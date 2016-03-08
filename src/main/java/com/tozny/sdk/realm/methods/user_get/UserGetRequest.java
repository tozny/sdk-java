package com.tozny.sdk.realm.methods.user_get;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiRequest;

import javax.annotation.Nullable;

/**
 * Collects request parameters for invoking the "realm.user_get" method.
 * Requires that either the `user_id` or `tozny_email` field be set.
 */
public class UserGetRequest implements ToznyApiRequest {

    @JsonProperty private String method = "realm.user_get";
    @JsonProperty private String user_id;
    @JsonProperty private String tozny_email;

    public UserGetRequest(@Nullable String user_id, @Nullable String tozny_email) {
        if (user_id == null && tozny_email == null) {
            throw new IllegalArgumentException("UserGetRequest requires a user_id or tozny_email parameter.");
        }
        this.user_id = user_id;
        this.tozny_email = tozny_email;
    }

    public String getMethod() {
        return method;
    }

    @Nullable
    public String getUserId() {
        return user_id;
    }

    @Nullable
    public String getToznyEmail() {
        return tozny_email;
    }

}
