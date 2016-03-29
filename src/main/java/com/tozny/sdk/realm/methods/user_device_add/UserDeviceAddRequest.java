package com.tozny.sdk.realm.methods.user_device_add;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiRequest;

/**
 * Collects request parameters for invoking the "realm.user_device_add" method.
 */
@JsonAutoDetect(getterVisibility=JsonAutoDetect.Visibility.NONE)
public class UserDeviceAddRequest implements ToznyApiRequest {

    @JsonProperty private String method = "realm.user_device_add";
    @JsonProperty private String user_id;

    public UserDeviceAddRequest(String user_id) {
        this.user_id = user_id;
    }

    public String getMethod() {
        return method;
    }

    public String getUserId() {
        return user_id;
    }

}
