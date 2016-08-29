package com.tozny.sdk.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiResponse;

import java.util.Date;
import java.util.List;

public class EnrollmentChallenge extends ToznyApiResponse<Void> {
    private final String user_id;
    private final String secret_enrollment_url;
    private final String secret_enrollment_qr_url;

    private final String temp_key;
    private final String key_id;
    private final Date created;

    @JsonCreator
    public EnrollmentChallenge(
            @JsonProperty("return")      String ret,
            @JsonProperty("count")       Integer count,
            @JsonProperty("total")       Integer total,
            @JsonProperty("errors")      List<ToznyApiException.ToznyApiError> errors,
            @JsonProperty("status_code") Integer status_code,

            // Common fields
            @JsonProperty("user_id")                  String user_id,
            @JsonProperty("secret_enrollment_url")    String secret_enrollment_url,
            @JsonProperty("secret_enrollment_qr_url") String secret_enrollment_qr_url,

            // User fields
            @JsonProperty("temp_key") String temp_key,
            @JsonProperty("key_id")   String key_id,
            @JsonProperty("created")  Date created,
            @JsonProperty("status")   Integer status
    ) {
        super(ret, null, null, count, total, errors);

        this.user_id = user_id;
        this.secret_enrollment_url = secret_enrollment_url;
        this.secret_enrollment_qr_url = secret_enrollment_qr_url;

        this.temp_key = temp_key;
        this.key_id = key_id;
        this.created = created;
    }

    public String getUserId() {
        return user_id;
    }

    public String getSecretEnrollmentUrl() {
        return secret_enrollment_url;
    }

    public String getSecretEnrollmentQrUrl() {
        return secret_enrollment_qr_url;
    }


    public String getTempKey() {
        return temp_key;
    }

    public String getKeyId() {
        return key_id;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "EnrollmentChallenge{" +
                "user_id='" + user_id + '\'' +
                ", temp_key='" + temp_key + '\'' +
                ", secret_enrollment_url='" + secret_enrollment_url + '\'' +
                ", secret_enrollment_qr_url='" + secret_enrollment_qr_url + '\'' +
                ", key_id='" + key_id + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
