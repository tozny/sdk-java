package com.tozny.sdk.realm.methods.user_exists;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiRequest;

import javax.annotation.Nullable;

/**
 * Constructs a Request for invoking the "realm.user_exists" method.
 */
@JsonAutoDetect(getterVisibility=JsonAutoDetect.Visibility.NONE)
public class UserExistsRequest implements ToznyApiRequest {

    @JsonProperty(required=true) private String method = "realm.user_exists";
    @JsonProperty private String user_id;
    @JsonProperty private String tozny_email;

    public UserExistsRequest(@Nullable String user_id, @Nullable String tozny_email) {
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
