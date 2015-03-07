package com.tozny.sdk.realm;

import com.google.api.client.util.Key;

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

    @Key
    public String user_id;

    @Key
    public Boolean blocked;

    @Key
    public Long login_attempts;

    @Key
    public String tmp_block_timestamp;

    @Key
    public Integer tmp_block_attempts;

    @Key
    public String status;

    @Key
    public Date created;

    @Key
    public Date modified;

    @Key
    public Date last_login;

    @Key
    public Long total_logins;

    @Key
    public Long total_failed_logins;

    @Key
    public Date last_failed_login;

    @Key
    public Integer total_devices;

    @Key
    public Map<String,String> meta;

    @Key
    public String tozny_primary;

    @Key
    public String tozny_secondary;

    @Key
    public String tozny_username;

    @Key
    public String tozny_email;


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