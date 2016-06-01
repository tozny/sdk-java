package com.tozny.sdk.realm.methods.users_get;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tozny.sdk.RealmApi;
import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiRequest;
import com.tozny.sdk.internal.ProtocolHelpers;
import com.tozny.sdk.internal.ToznyProtocol;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * Collects request parameters for invoking the "realm.users_get" method.
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class UsersGetRequest implements ToznyApiRequest {

    @JsonProperty(required=true) private String method = "realm.users_get";
    @JsonProperty private String term;
    @JsonProperty private String meta_advanced;
    @JsonProperty private String user_ids;
    @JsonProperty private Integer rows;

    public UsersGetRequest(
            @Nullable String term,
            @Nullable List<Map<String, String>> meta_advanced,
            @Nullable List<String> user_ids,
            @Nullable Integer rows) {
        this.term = term;
        this.rows = (rows == null) ? 1 : rows;

        if ( user_ids != null ) {
            this.user_ids = String.join(",", user_ids);
        }

        if ( meta_advanced != null ) {
            try {
                byte[] json = new ObjectMapper().writeValueAsBytes(meta_advanced);
                this.meta_advanced = ProtocolHelpers.base64UrlEncode(json);
            }
            catch (JsonProcessingException e ) {
                throw new ToznyApiException("Error while serializing users_get JSON", e);
            }
        }
    }

    public String getMethod() {
        return method;
    }

    @Nullable
    public String getTerm() {
        return term;
    }

    @Nullable
    public String getMeta_advanced() {
        return meta_advanced;
    }

    @Nullable
    public String getUser_ids() {
        return user_ids;
    }

    @Nullable
    public Integer getRows() {
        return rows;
    }
}
