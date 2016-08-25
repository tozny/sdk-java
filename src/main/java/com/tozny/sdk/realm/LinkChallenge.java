package com.tozny.sdk.realm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiResponse;

import javax.annotation.Nullable;
import java.net.URL;
import java.util.List;

/**
 * Holds the Tozny API response for a link challenge.
 *
 * An example realm.link_challenge response:
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
public class LinkChallenge extends ToznyApiResponse<Void> {

    private final String session_id;
    private final String presence;
    private final URL url;

    @JsonCreator
    public LinkChallenge(
            @JsonProperty("return") String ret,
            @JsonProperty("count")  Integer count,
            @JsonProperty("total")  Integer total,
            @JsonProperty("errors") List<ToznyApiException.ToznyApiError> errors,

            @JsonProperty("session_id")    String session_id,
            @JsonProperty("presence")      String presence,
            @Nullable @JsonProperty("url") URL url
    ) {
        super(ret, null, null, count, total, errors);

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

    @Nullable
    public URL getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "LinkChallenge{" +
                "session_id='" + session_id + '\'' +
                ", presence='" + presence + '\'' +
                ", url=" + url +
                '}';
    }
}
