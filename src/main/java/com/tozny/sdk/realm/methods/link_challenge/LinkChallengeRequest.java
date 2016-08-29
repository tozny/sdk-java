package com.tozny.sdk.realm.methods.link_challenge;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiRequest;

import javax.annotation.Nullable;

/**
 * Constructs a Request for invoking the "realm.link_challenge" method.
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class LinkChallengeRequest implements ToznyApiRequest {
    @JsonProperty(required=true) private String method = "realm.link_challenge";
    @JsonProperty(required=true) private String destination;
    @JsonProperty(required=true) private String endpoint;
    @JsonProperty private Integer lifespan;
    @JsonProperty private String context;
    @JsonProperty private String send;
    @JsonProperty private String data;

    public LinkChallengeRequest(String destination, String endpoint, @Nullable Integer lifespan, @Nullable String context, @Nullable Boolean send, @Nullable String data) {
        this.destination = destination;
        this.endpoint = endpoint;
        this.lifespan = lifespan;
        this.context = context;
        this.data = data;

        if (send == null || send ) {
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

    public String getEndpoint() {
        return endpoint;
    }

    @Nullable
    public Integer getLifespan() {
        return lifespan;
    }

    @Nullable
    public String getContext() {
        return context;
    }

    @Nullable
    public Boolean getSend() {
        return send.equals("yes");
    }

    @Nullable
    public String getData() {
        return data;
    }
}
