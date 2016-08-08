package com.tozny.sdk.realm.methods.email_challenge;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiRequest;

import javax.annotation.Nullable;

/**
 * Constructs a Request for invoking the "realm.email_challenge" method.
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class EmailChallengeRequest implements ToznyApiRequest {
    @JsonProperty(required=true) private String method = "realm.email_challenge";
    @JsonProperty(required=true) private String destination;
    @JsonProperty private String callback;
    @JsonProperty private String hostname;
    @JsonProperty private String send;

    public EmailChallengeRequest(String destination, @Nullable String callback, @Nullable String hostname, @Nullable Boolean send) {
        this.destination = destination;
        this.callback = callback;
        this.hostname = hostname;

        if (send == null) {
            this.send = "yes";
        } else {
            this.send = "no";
        }
    }

    public String getMethod() {
        return method;
    }

    public String getDestination() {
        return destination;
    }

    @Nullable
    public String getCallback() {
        return callback;
    }

    @Nullable
    public String getHostname() {
        return hostname;
    }

    @Nullable
    public Boolean getSend() {
        return send.equals("yes");
    }
}
