package com.tozny.sdk.realm.methods.user_add;

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

public class UserAddResponse extends ToznyApiResponse<Void> {

    private final String username;
    private final String user_id;
    private final String user_secret;
    private final String user_temp_key;
    private final String secret_enrollment_url;
    private final String secret_enrollment_qr_url;
    private final String logo_url;
    private final String info_url;
    private final String crypto_suite;
    private final String display_name;

    @JsonCreator
    public UserAddResponse(
            @JsonProperty("return")  String ret,
            @JsonProperty("count")   Integer count,
            @JsonProperty("total")   Integer total,
            @JsonProperty("errors")  List<ToznyApiError> errors,

            @JsonProperty("username")                 String username,
            @JsonProperty("user_id")                  String user_id,
            @JsonProperty("user_secret")              String user_secret,
            @JsonProperty("user_temp_key")            String user_temp_key,
            @JsonProperty("secret_enrollment_url")    String secret_enrollment_url,
            @JsonProperty("secret_enrollment_qr_url") String secret_enrollment_qr_url,
            @JsonProperty("logo_url")                 String logo_url,
            @JsonProperty("info_url")                 String info_url,
            @JsonProperty("crypto_suite")             String crypto_suite,
            @JsonProperty("display_name")             String display_name) {
        super(ret, null, null, count, total, errors);
        this.username = username;
        this.user_id = user_id;
        this.user_secret = user_secret;
        this.user_temp_key = user_temp_key;
        this.secret_enrollment_url = secret_enrollment_url;
        this.secret_enrollment_qr_url = secret_enrollment_qr_url;
        this.logo_url = logo_url;
        this.info_url = info_url;
        this.crypto_suite = crypto_suite;
        this.display_name = display_name;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return user_id;
    }

    public String getUserSecret() {
        return user_secret;
    }

    public String getUserTempKey() {
        return user_temp_key;
    }

    public String getSecretEnrollmentUrl() {
        return secret_enrollment_url;
    }

    public String getSecretEnrollmentQrUrl() {
        return secret_enrollment_qr_url;
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

}
