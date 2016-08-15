package com.tozny.sdk.realm.methods.otp_challenge;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiRequest;

import javax.annotation.Nullable;

/**
 * Constructs a Request for invoking the "realm.otp_challenge" method
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class OTPChallengeRequest implements ToznyApiRequest {
    @JsonProperty(required=true) private String method = "realm.lotp_challenge";
    @JsonProperty private String type;
    @JsonProperty private String context;
    @JsonProperty private String destination;
    @JsonProperty private String presence;
    @JsonProperty private String data;

    public OTPChallengeRequest(@Nullable String type, @Nullable String context, @Nullable String destination, @Nullable String presence, @Nullable String data) {
        this.type = type;
        this.context = context;
        this.destination = destination;
        this.presence = presence;
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    @Nullable
    public String getType() {
        return type;
    }

    @Nullable
    public String getContext() {
        return context;
    }

    @Nullable
    public String getDestination() {
        return destination;
    }

    @Nullable
    public String getPresence() {
        return presence;
    }

    @Nullable
    public String getData() {
        return data;
    }
}
