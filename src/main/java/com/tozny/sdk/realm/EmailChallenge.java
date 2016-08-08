package com.tozny.sdk.realm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiResponse;

import java.net.URL;

/**
 * Holds the Tozny API response for an email challenge.
 *
 * An example realm.email_challenge response:
 * <pre>
 * {@code
 * {
 *     "return": "ok",
 *     "status_code": 200,
 *     "session_id": "acd8280fb2ef27d3c876c1aabe10ec3ee7ba0372ca3858125909e05b3f3324da",
 *     "presence": "9c85c8114a019bb4f80c01a1b0550259d865fef82520811b8dd09df83919ff43",
 *     "url": "https://otp.api.tozny.com/sid_574dfb964ba74/a4887efd5e262ce26675473949f176b7"
 * }
 * }
 * </pre>
 */
public class EmailChallenge extends ToznyApiResponse<Void> {

    private final String session_id;
    private final String presence;
    private final URL url;

    @JsonCreator
    public EmailChallenge(
            @JsonProperty("return") String ret,

            @JsonProperty("session_id") String session_id,
            @JsonProperty("presence")   String presence,
            @JsonProperty("url")        URL url
    ) {
        super(ret, null, null, null, null, null);

        this.session_id = session_id;
        this.presence = presence;
        this.url = url;
    }

    public String getSessionId() {
        return session_id;
    }

    public String getPresence() {
        return presence;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "EmailChallenge{" +
                "session_id='" + session_id + '\'' +
                ", presence='" + presence + '\'' +
                ", url=" + url +
                '}';
    }
}
