package com.tozny.sdk.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiResponse;
import com.tozny.sdk.ToznyApiRequest;
import com.tozny.sdk.realm.RealmConfig;

import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;

/**
 * Low-level methods for constructing and dispatching API requests.
 */
public class ToznyProtocol {

    private final RealmConfig realmConfig;
    private final ObjectMapper mapper;
    private final OkHttpClient client;

    /**
     * @param realmConfig provides realm credentials and API URL.
     * @param httpClient the OkHttpClient instance to make calls out to Tozny's API servers with.
     * @param objectMapper the Jackson ObjectMapper instance to use when constructing or manipulating JSON structures.
     */

    public ToznyProtocol(RealmConfig realmConfig, OkHttpClient httpClient, ObjectMapper objectMapper) {
        this.realmConfig = realmConfig;
        this.mapper = objectMapper;
        this.client = httpClient;
    }

    public ToznyProtocol(RealmConfig realmConfig) {
        this(realmConfig, getDefaultHttpClient(), getDefaultObjectMapper());
    }

    /**
     * Sends an HTTP request based on the given API request object.
     * The Response is them passed to an instance of the given dataClass for
     * marshaling into the result type.
     *
     * @param req The Tozny API Request object
     * @param dataClass The response marshalling class.
     * @param <T> A descendant of ToznyApiResponse, which will provide helpers for managing ToznyAPI errors.
     * @return an instance of the given dataClass.
     * @throws ToznyApiException if an I/O or protocol error occurs during the API call
     */
    public <T extends ToznyApiResponse> T dispatch(
            ToznyApiRequest req,
            Class<T> dataClass) throws ToznyApiException {
        RequestMeta meta = new RequestMeta(
                ProtocolHelpers.getNonce(),
                ProtocolHelpers.getExpires(),
                realmConfig.realmKeyId.value
                );
        byte[] json = toJson(req, meta);
        String signed_data = ProtocolHelpers.base64UrlEncode(json);
        String signature   = ProtocolHelpers.sign(realmConfig.realmSecret, signed_data);

        RequestBody params = new FormBody.Builder()
            .add("signed_data", signedData)
            .add("signature", signature)
            .build();

        Request request = new Request.Builder()
            .url(realmConfig.apiUrl)
            .header("Accept", "application/json")
            .post(params)
            .build();

        Response response;
        try {
            response = client.newCall(request).execute();
        }
        catch (IOException e) {
            String message = "While calling "+req+".";
            throw new ToznyApiException(message, e);
        }

        if (!response.isSuccessful()) {
            String message = response.message() != null ? response.message() : "";
            String location = "";  // TODO
            throw new ToznyApiException(Collections.singletonList(
                        new ToznyApiException.ToznyApiError(
                            response.code(), message, location
                            )
                        ));
        }

        T apiResponse;
        try {
            apiResponse = mapper.readValue(response.body().byteStream(), dataClass);
        }
        catch (JsonMappingException e) {
            String message = "While calling "+req+".";
            throw new ToznyApiException(message, e);
        }

        if (apiResponse.isError()) {
            throw apiResponse.getException();
        }
        else {
            return apiResponse;
        }
    }

    private byte[] toJson(ToznyApiRequest req, RequestMeta meta) {
        ObjectNode merged = mergeJson(req, meta);
        return mapper.writeValueAsBytes(merged);
    }

    private ObjectNode mergeJson(Object target, Object source) {
        ObjectNode mainNode = mapper.valueToTree(target);
        ObjectNode updateNode = mapper.valueToTree(source);
        for (String fieldName : updateNode.fieldNames()) {
            JsonNode value = updateNode.get(fieldName);
            mainNode.put(fieldName, value);
        }
        return mainNode;
    }

    public static class RequestMeta {
        @JsonProperty private String nonce;
        @JsonProperty private String expires_at;
        @JsonProperty private String realm_key_id;

        public RequestMeta(String nonce, String expires_at, String realm_key_id) {
            this.nonce = nonce;
            this.expires_at = expires_at;
            this.realm_key_id = realm_key_id;
        }
    }

    private static OkHttpClient getDefaultHttpClient() {
        return new OkHttpClient();
    }

    private static ObjectMapper getDefaultObjectMapper() {
        SimpleModule toznyModule = new SimpleModule("ToznyModule", new Version(1, 0, 0, null));
        toznyModule.addDeserializer(new DateDeserializer());
        toznyModule.addSerializer(new Base64Serializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.register(toznyModule);

        return mapper;
    }

}
