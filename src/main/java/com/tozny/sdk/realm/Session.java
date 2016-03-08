package com.tozny.sdk.realm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.realm.config.ToznyRealmKeyId;

import java.lang.Void;
import java.net.URL;
import java.util.Date;

/**
 * Holds the Tozny API response for a Session challenge.
 *
 * An example realm.question_challenge response:
 * <pre>
 * {@code
 * {
 *     "return": "ok",
 *     "status_code": 200,
 *     "challenge": "3cfe1bf14e",
 *     "session_id": "acd8280fb2ef27d3c876c1aabe10ec3ee7ba0372ca3858125909e05b3f3324da",
 *     "realm_key_id": "sid_52fa6d0a3a290",
 *     "qr_url": "https://api.tozny.com?method=user.qr_question_challenge&realm_key_id=sid_52fa6d0a3a290&session_id=acd8280fb2ef27d3c876c1aabe10ec3ee7ba0372ca3858125909e05b3f3324da&challenge=3cfe1bf14e&created_at=1425670790&q=1",
 *     "mobile_url": "tozauth://api.tozny.com/?c=3cfe1bf14e&r=sid_52fa6d0a3a290&s=acd8280fb2ef27d3c876c1aabe10ec3ee7ba0372ca3858125909e05b3f3324da&t=1425670790&q=1",
 *     "created_at": 1425670790,
 *     "presence": "9c85c8114a019bb4f80c01a1b0550259d865fef82520811b8dd09df83919ff43"
 * }
 * }
 * </pre>
 */
public class Session extends ToznyApiResponse<Void> {

    @JsonProperty private String challenge;
    @JsonProperty private String realm_key_id;
    @JsonProperty private String session_id;
    @JsonProperty private URL qr_url;
    @JsonProperty private String mobile_url;
    @JsonProperty private String presence;
    @JsonProperty private Date created_at;

    public String getChallenge() {
        return challenge;
    }

    public ToznyRealmKeyId getRealmKeyId() {
        return new ToznyRealmKeyId(realm_key_id);
    }

    public String getSessionId() {
        return session_id;
    }

    public URL getQrUrl() {
        return qr_url;
    }

    /**
     * `mobile_url` is given as `String` instead of `URL` because the custom
     * scheme confuses Java URL parsing.
     */
    public String getMobileUrl() {
        return mobile_url;
    }

    public String getPresence() {
        return presence;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    @Override
    public String toString() {
        return "Session{" +
                "challenge='" + challenge + '\'' +
                ", realm_key_id='" + realm_key_id + '\'' +
                ", session_id='" + session_id + '\'' +
                ", qr_url=" + qr_url +
                ", mobile_url=" + mobile_url +
                ", presence='" + presence + '\'' +
                ", created_at=" + created_at +
                '}';
    }

}
