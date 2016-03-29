package com.tozny.sdk.realm.methods.check_valid_login;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiRequest;

@JsonAutoDetect(getterVisibility=JsonAutoDetect.Visibility.NONE)
public class CheckValidLoginRequest implements ToznyApiRequest {

    @JsonProperty(required=true) private String method = "realm.check_valid_login";
    @JsonProperty(required=true) private String user_id;
    @JsonProperty(required=true) private String session_id;

    public CheckValidLoginRequest(String user_id, String session_id) {
        this.user_id = user_id;
        this.session_id = session_id;
    }

    public String getMethod() {
        return method;
    }

    public String getUserId() {
        return user_id;
    }

    public String getSessionId() {
        return session_id;
    }

}
