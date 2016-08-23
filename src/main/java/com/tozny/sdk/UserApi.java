package com.tozny.sdk;

import com.tozny.sdk.realm.LinkChallenge;
import com.tozny.sdk.realm.OTPChallenge;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Main entry point to the Tozny API's userland calls
 *
 * To make API calls, you will need a Tozny Realm Key ID, which can be
 * obtained from the Tozny Admin Console (admin.tozny.com).
 */
public class UserApi {

    private final String apiUrl;
    private final String realmKeyId;
    private final Call.Factory client;

    public UserApi(String realmKeyId) {
        this("https://api.tozny.com/", realmKeyId);
    }

    public UserApi(String apiUrl, String realmKeyId) {
        this.apiUrl = apiUrl;
        this.realmKeyId = realmKeyId;
        this.client = new OkHttpClient();
    }

    public InputStream linkChallenge(String destination, String context, String callback, String hostname) {
        Map<String, String> args = new HashMap<String, String>() {{
            put("destination", destination);
            put("context", context);
            put("callback", callback);
            put("hostname", hostname);
        }};
        return rawCall("user.link_challenge", args);
    }

    public boolean otpChallenge(String type, String context, String destination, String presence, String data) {
        return false;
    }

    public boolean linkResult() {
        return false;
    }

    public boolean otpResult() {
        return false;
    }

    public boolean challengeExchange() {
        return false;
    }

    private InputStream rawCall(String method, Map<String, String> parameters) throws ToznyApiException {
        FormBody.Builder requestBuilder = new FormBody.Builder()
                .add("method", method)
                .add("realm_key_id", realmKeyId);

        for(Map.Entry<String, String> entry: parameters.entrySet()) {
            if ( entry.getValue() != null ) {
                requestBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        RequestBody params = requestBuilder.build();

        Request request = new Request.Builder()
                .url(apiUrl)
                .header("Accept", "application/json")
                .post(params)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
        }
        catch (IOException e) {
            String message = "While calling " + method + ".";
            throw new ToznyApiException(message, e);
        }

        if (! response.isSuccessful()) {
            response.body().close();
            String message = response.message() != null ? response.message() : "";

            throw new ToznyApiException(Collections.singletonList(
                    new ToznyApiException.ToznyApiError(
                            response.code(), message, method
                    )
            ));
        }

        return response.body().byteStream();
    }
}
