package com.tozny.sdk.realm.methods.user_add;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiRequest;

import java.util.Map;
import javax.annotation.Nullable;

/**
 * Collects request parameters for invoking the "realm.user_get" method.
 * Requires that either the `user_id` or `tozny_email` field be set.
 */
public class UserAddRequest implements ToznyApiRequest {

    @JsonProperty private String method = "realm.user_add";
    @JsonProperty private String defer;
    @JsonProperty private String pub_key;
    @JsonProperty private Map<String,String> extra_fields;

    public UserAddRequest(
            boolean defer,
            @Nullable Map<String,String> extra_fields,
            @Nullable String pub_key) {
        this.defer = defer ? "true" : "false";
        this.user_id = user_id;
        this.tozny_email = tozny_email;
    }

    public String getMethod() {
        return method;
    }

    public boolean getDefer() {
        return defer.equals("true");
    }

    @Nullable
    public Map<String,String> getExtraFields() {
        return extra_fields;
    }

    @Nullable
    public String getPubKey() {
        return pub_key;
    }

}
