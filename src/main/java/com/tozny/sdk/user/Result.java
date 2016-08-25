package com.tozny.sdk.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiResponse;

import javax.annotation.Nullable;
import java.util.List;

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
 *     "signature": "JE5qs7hVmQi_Xyt2hMtZEgIwUYinfJbDwOKxTQ2hdQQ",
 *     "ua": "browser"
 * }
 * }
 * </pre>
 */
public class Result extends ToznyApiResponse<Void> {

    private final String signed_data;
    private final String signature;
    private final String ua;

    @JsonCreator
    public Result(
            @JsonProperty("return")      String ret,
            @JsonProperty("count")       Integer count,
            @JsonProperty("total")       Integer total,
            @JsonProperty("errors")      List<ToznyApiException.ToznyApiError> errors,
            @JsonProperty("status_code") Integer status_code,

            @JsonProperty("signed_data")        String signed_data,
            @JsonProperty("signature")          String signature,
            @Nullable @JsonProperty("ua")       String ua
    ) {
        super(ret, null, null, count, total, errors);

        this.signed_data = signed_data;
        this.signature = signature;
        this.ua = ua;
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

    @Override
    public String toString() {
        return "Result{" +
                "signed_data='" + signed_data + '\'' +
                ", signature='" + signature + '\'' +
                ", ua='" + ua + '\'' +
                '}';
    }
}
