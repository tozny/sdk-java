package com.tozny.sdk.realm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

/**
 * Holds the Tozny API response for a Realm User.
 *
 * An example realm.user_get response:
 *
 * <pre>
 * {
 *     "return": "ok",
 *     "status_code": 200,
 *     "user_id": "sid_5302bde69d72f",
 *     "results": {
 *         "user_id": "sid_5302bde69d72f",
 *         "blocked": null,
 *         "login_attempts": null,
 *         "tmp_block_timestamp": null,
 *         "tmp_block_attempts": null,
 *         "status_code": "1",
 *         "created": "1392688614",
 *         "modified": "1425579460",
 *         "last_login": "1407800811",
 *         "total_logins": "16",
 *         "total_failed_logins": "4",
 *         "last_failed_login": "1407777001",
 *         "total_devices": "6",
 *         "meta": {
 *             "username": "kirk",
 *             "email": "kirk@tozny.com",
 *             "transaction_53e8f9f87e844": "eyJ0eXBlIjoiZGVwb3NpdCIsImRlc2NyaXB0aW9uIjoiT25saW5lIERlcG9zaXQiLCJhbW91bnQiOjc4MDYsIm51bWJlciI6IjUzZThmOWY4N2U4NDQiLCJkYXRlIjoxNDA3Nzc3MjcyfQ",
 *             "transaction_53e8f9f87e8c6": "eyJ0eXBlIjoiZGVwb3NpdCIsImRlc2NyaXB0aW9uIjoiT25saW5lIERlcG9zaXQiLCJhbW91bnQiOjk1MjksIm51bWJlciI6IjUzZThmOWY4N2U4YzYiLCJkYXRlIjoxNDA3Nzc3MjcyfQ"
 *         },
 *         "tozny_primary": "kirk@tozny.com ",
 *         "tozny_secondary": "kirk ",
 *         "tozny_username": "kirk",
 *         "tozny_email": "kirk@tozny.com"
 *     }
 * }
 * </pre>
 */
public class User {

    @JsonProperty private String user_id;
    @JsonProperty private Boolean blocked;
    @JsonProperty private Long login_attempts;
    @JsonProperty private String tmp_block_timestamp;
    @JsonProperty private Integer tmp_block_attempts;
    @JsonProperty private String status;
    @JsonProperty private Date created;
    @JsonProperty private Date modified;
    @JsonProperty private Date last_login;
    @JsonProperty private Long total_logins;
    @JsonProperty private Long total_failed_logins;
    @JsonProperty private Date last_failed_login;
    @JsonProperty private Integer total_devices;
    @JsonProperty private Map<String,String> meta;
    @JsonProperty private String tozny_primary;
    @JsonProperty private String tozny_secondary;
    @JsonProperty private String tozny_username;
    @JsonProperty private String tozny_email;

    public String getUserId() {
        return user_id;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public Long getLoginAttempts() {
        return login_attempts;
    }

    public String getTmpBlockTimestamp() {
        return tmp_block_timestamp;
    }

    public Integer getTmpBlockAttempts() {
        return tmp_block_attempts;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public Date getLastLogin() {
        return last_login;
    }

    public Long getTotalLogins() {
        return total_logins;
    }

    public Long getTotalFailedLogins() {
        return total_failed_logins;
    }

    public Date getLastFailedLogin() {
        return last_failed_login;
    }

    public Integer getTotalDevices() {
        return total_devices;
    }

    public Map<String,String> getMeta() {
        return meta;
    }

    public String getToznyPrimary() {
        return tozny_primary;
    }

    public String getToznySecondary() {
        return tozny_secondary;
    }

    public String getToznyUsername() {
        return tozny_username;
    }

    public String getToznyEmail() {
        return tozny_email;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", blocked=" + blocked +
                ", login_attempts=" + login_attempts +
                ", tmp_block_timestamp='" + tmp_block_timestamp + '\'' +
                ", tmp_block_attempts='" + tmp_block_attempts + '\'' +
                ", status='" + status + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", last_login=" + last_login +
                ", total_logins=" + total_logins +
                ", total_failed_logins=" + total_failed_logins +
                ", last_failed_login=" + last_failed_login +
                ", total_devices=" + total_devices +
                ", meta=" + meta +
                ", tozny_primary='" + tozny_primary + '\'' +
                ", tozny_secondary='" + tozny_secondary + '\'' +
                ", tozny_username='" + tozny_username + '\'' +
                ", tozny_email='" + tozny_email + '\'' +
                '}';
    }
}
