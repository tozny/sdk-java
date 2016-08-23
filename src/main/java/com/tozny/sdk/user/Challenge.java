package com.tozny.sdk.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiResponse;

/**
 * Holds the Tozny API response for an OTP or link challenge.
 *
 * An example user.link_challenge response:
 * <pre>
 * {@code
 * {
 *     "return": "ok",
 *     "status_code": 200,
 *     "session_id": "acd8280fb2ef27d3c876c1aabe10ec3ee7ba0372ca3858125909e05b3f3324da",
 *     "presence": "9c85c8114a019bb4f80c01a1b0550259d865fef82520811b8dd09df83919ff43"
 * }
 * }
 * </pre>
 */
public class Challenge extends ToznyApiResponse<Void> {

    private final String session_id;
    private final String presence;

    @JsonCreator
    public Challenge(
            @JsonProperty("return") String ret,
            @JsonProperty("status_code") Integer status_code,

            @JsonProperty("session_id")    String session_id,
            @JsonProperty("presence")      String presence
    ) {
        super(ret, null, null, null, null, null);

        this.session_id = session_id;
        this.presence = presence;
    }

    public String getSessionId() {
        return session_id;
    }

    public String getPresence() {
        return presence;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "session_id='" + session_id + '\'' +
                ", presence='" + presence +
                '}';
    }
}
