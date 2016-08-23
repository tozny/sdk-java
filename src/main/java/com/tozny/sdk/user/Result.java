package com.tozny.sdk.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiResponse;

import javax.annotation.Nullable;

/**
 * Holds the Tozny API response for an OTP or link result.
 *
 * An example user.otp_result response:
 * <pre>
 * {@code
 * {
 *     "return": "ok",
 *     "status_code": 200,
 *     "signed_data": "eyJkZXN0aW5hdGlvbiI6IisxNTAzNTU1NTU1NSIsInR5cGUiOiJzbXMtb3RwLTYiLCJjb250ZXh0IjoiYXV0aGVudGljYXRlIiwibm9uY2UiOiIxMjM0NTY3ODkwIiwiZXhwaXJlc19hdCI6MTQ4MTg5OTM1Mn0",
 *     "signature": "JE5qs7hVmQi_Xyt2hMtZEgIwUYinfJbDwOKxTQ2hdQQ"
 * }
 * }
 * </pre>
 */
public class Result extends ToznyApiResponse<Void> {

    private final String signed_data;
    private final String signature;
    private final String ua;
    private final String callback;

    @JsonCreator
    public Result(
            @JsonProperty("return") String ret,
            @JsonProperty("status_code") Integer status_code,

            @JsonProperty("signed_data")        String signed_data,
            @JsonProperty("signature")          String signature,
            @Nullable @JsonProperty("callback") String callback,
            @Nullable @JsonProperty("ua")       String ua
    ) {
        super(ret, null, null, null, null, null);

        this.signed_data = signed_data;
        this.signature = signature;
        this.ua = ua;
        this.callback = callback;
    }

    public String getSigned_data() {
        return signed_data;
    }

    public String getSignature() {
        return signature;
    }

    @Nullable
    public String getUa() {
        return ua;
    }

    @Nullable
    public String getCallback() {
        return callback;
    }

    @Override
    public String toString() {
        return "Result{" +
                "signed_data='" + signed_data + '\'' +
                ", signature='" + signature + '\'' +
                ", ua='" + ua + '\'' +
                ", callback='" + callback + '\'' +
                '}';
    }
}
