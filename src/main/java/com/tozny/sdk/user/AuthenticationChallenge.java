package com.tozny.sdk.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiResponse;

import java.util.List;

public class AuthenticationChallenge extends ToznyApiResponse<Void> {
    private final String signed_data;
    private final String signature;

    public AuthenticationChallenge(
            @JsonProperty("return")      String ret,
            @JsonProperty("count")       Integer count,
            @JsonProperty("total")       Integer total,
            @JsonProperty("errors")      List<ToznyApiException.ToznyApiError> errors,
            @JsonProperty("status_code") Integer status_code,

            @JsonProperty("signed_data") String signed_data,
            @JsonProperty("signature")   String signature
    ) {
        super(ret, null, null, count, total, errors);

        this.signed_data = signed_data;
        this.signature = signature;
    }

    public String getSignedData() {
        return signed_data;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return "AuthenticationChallenge{" +
                "signed_data='" + signed_data + '\'' +
                ", signature='" + signature +
                '}';
    }
}
