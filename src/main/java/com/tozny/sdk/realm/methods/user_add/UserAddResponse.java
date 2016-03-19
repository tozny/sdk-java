package com.tozny.sdk.realm.methods.user_add;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiResponse;
import com.tozny.sdk.realm.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A GenericJson instance intended for marshaling an instance of User.
 */
public class UserAddResponse extends ToznyApiResponse<Void> {

    @JsonProperty private String username;
    @JsonProperty private String user_id;
    @JsonProperty private String user_temp_key;
    @JsonProperty private String secret_enrollment_url;
    @JsonProperty private String secret_enrollment_qr_url;
    @JsonProperty private String logo_url;
    @JsonProperty private String info_url;
    @JsonProperty private String crypto_suite;
    @JsonProperty private String display_name;

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return user_id;
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
