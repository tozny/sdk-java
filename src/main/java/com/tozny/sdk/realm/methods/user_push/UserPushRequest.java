package com.tozny.sdk.realm.methods.user_push;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiRequest;

import javax.annotation.Nullable;

/**
 * Collects request parameters for invoking the "realm.user_push" method.
 * Requires that either the `user_id` or `email` or `username` field be set.
 */
@JsonAutoDetect(getterVisibility=JsonAutoDetect.Visibility.NONE)
public class UserPushRequest implements ToznyApiRequest {

    @JsonProperty private String method = "realm.user_push";
    @JsonProperty private String session_id;
    @JsonProperty private String user_id;
    @JsonProperty private String email;
    @JsonProperty private String username;

    public UserPushRequest(String session_id, @Nullable String user_id, @Nullable String email, @Nullable String username) {
        if (user_id == null && email == null && username == null) {
            throw new IllegalArgumentException("UserPushRequest requires a user_id, email, or username parameter.");
        }
        this.session_id = session_id;
        this.user_id = user_id;
        this.email = email;
        this.username = username;
    }

    public String getMethod() {
        return method;
    }

    public String getSessionId() {
        return session_id;
    }

    @Nullable
    public String getUserId() {
        return user_id;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    @Nullable
    public String getUsername() {
        return username;
    }
}
