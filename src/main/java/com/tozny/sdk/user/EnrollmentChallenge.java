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

    private final String username;
    private final String user_secret;
    private final String temp_user_secret;
    private final String logo_url;
    private final String info_url;
    private final String crypto_suite;
    private final String display_name;

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
            @JsonProperty("status")      Integer status,

            // Common fields
            @JsonProperty("user_id")                  String user_id,
            @JsonProperty("secret_enrollment_url")    String secret_enrollment_url,
            @JsonProperty("secret_enrollment_qr_url") String secret_enrollment_qr_url,

            // Device fields
            @JsonProperty("username")                 String username,
            @JsonProperty("user_secret")              String user_secret,
            @JsonProperty("temp_user_secret")         String temp_user_secret,
            @JsonProperty("logo_url")                 String logo_url,
            @JsonProperty("info_url")                 String info_url,
            @JsonProperty("crypto_suite")             String crypto_suite,
            @JsonProperty("display_name")             String display_name,

            // User fields
            @JsonProperty("temp_user_secret")         String temp_key,
            @JsonProperty("user_key_id")              String key_id,
            @JsonProperty("created") Date created
    ) {
        super(ret, null, null, count, total, errors);

        this.user_id = user_id;
        this.secret_enrollment_url = secret_enrollment_url;
        this.secret_enrollment_qr_url = secret_enrollment_qr_url;

        this.username = username;
        this.user_secret = user_secret;
        this.temp_user_secret = temp_user_secret;
        this.logo_url = logo_url;
        this.info_url = info_url;
        this.crypto_suite = crypto_suite;
        this.display_name = display_name;

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


    public String getUsername() {
        return username;
    }

    public String getUserSecret() {
        return user_secret;
    }

    public String getTempUserSecret() {
        return temp_user_secret;
    }

    public String getLogoUrl() {
        return logo_url;
    }

    public String getInfoUrl() {
        return info_url;
    }

    public String getCryptoSuite() {
        return crypto_suite;
    }

    public String getDisplayName() {
        return display_name;
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
        if ( temp_key.isEmpty() ) {
            return "UserEnrollmentChallenge{" +
                    "username='" + username + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", user_secret='" + user_secret + '\'' +
                    ", temp_user_secret='" + temp_user_secret + '\'' +
                    ", secret_enrollment_url='" + secret_enrollment_url + '\'' +
                    ", secret_enrollment_qr_url='" + secret_enrollment_qr_url + '\'' +
                    ", logo_url='" + logo_url + '\'' +
                    ", info_url='" + info_url + '\'' +
                    ", crypto_suite='" + crypto_suite + '\'' +
                    ", display_name='" + display_name + '\'' +
                    '}';
        } else {
            return "DeviceEnrollmentChallenge{" +
                    "user_id='" + user_id + '\'' +
                    ", temp_key='" + temp_key + '\'' +
                    ", secret_enrollment_url='" + secret_enrollment_url + '\'' +
                    ", secret_enrollment_qr_url='" + secret_enrollment_qr_url + '\'' +
                    ", key_id='" + key_id + '\'' +
                    ", created='" + created + '\'' +
                    '}';
        }


    }
}
