package com.tozny.sdk.realm.methods.user_device_add;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiException.ToznyApiError;
import com.tozny.sdk.ToznyApiResponse;
import com.tozny.sdk.realm.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDeviceAddResponse extends ToznyApiResponse<Void> {

    private final String user_id;
    private final String temp_key;
    private final String secret_enrollment_url;
    private final String secret_enrollment_qr_url;
    private final String key_id;
    private final Date created;

    @JsonCreator
    public UserDeviceAddResponse(
            @JsonProperty("return") String ret,
            @JsonProperty("errors") List<ToznyApiError> errors,

            @JsonProperty("user_id")                  String user_id,
            @JsonProperty("temp_key")                 String temp_key,
            @JsonProperty("secret_enrollment_url")    String secret_enrollment_url,
            @JsonProperty("secret_enrollment_qr_url") String secret_enrollment_qr_url,
            @JsonProperty("key_id")                   String key_id,
            @JsonProperty("created")                  Date created) {
        super(ret, null, null, null, null, errors);
        this.user_id = user_id;
        this.temp_key = temp_key;
        this.secret_enrollment_url = secret_enrollment_url;
        this.secret_enrollment_qr_url = secret_enrollment_qr_url;
        this.key_id = key_id;
        this.created = created;
    }

    public String getUserId() { return user_id; }
    public String getTempKey() { return temp_key; }
    public String getSecretEnrollmentUrl() { return secret_enrollment_url; }
    public String getSecretEnrollmentQrUrl() { return secret_enrollment_qr_url; }
    public String getKeyId() { return key_id; }
    public Date getCreated() { return created; }

}
