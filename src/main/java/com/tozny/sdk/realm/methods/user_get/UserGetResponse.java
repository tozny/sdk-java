package com.tozny.sdk.realm.methods.user_get;


import com.google.api.client.util.Data;
import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiResponse;
import com.tozny.sdk.realm.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A GenericJson instance intended for marshaling an instance of User.
 */
public class UserGetResponse extends ToznyApiResponse {

    private static final Logger LOG = Logger.getLogger(UserGetResponse.class.getName());

    /**
     * @return the Realm's User record.
     * @throws ToznyApiException if we are unable to convert the JSON response into a User type
     */
    public User getUser () throws ToznyApiException {
        Object resultsObject = this.get("results");
        if (resultsObject == null || !(resultsObject instanceof Map)) {
            throw new ToznyApiException("Expected the response to contain a JSON property named 'results'.");
        }
        Map<String, Object> results = (Map<String,Object>)resultsObject;

        User user = new User();

        user_id:
        {
            Object value = results.get("user_id");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                user.user_id = (String) value;
            }
        }

        blocked:
        {
            Object value = results.get("blocked");
            if (value != null && !Data.isNull(value) && (value instanceof Boolean)) {
                user.blocked = (Boolean) value;
            }
        }

        login_attempts:
        {
            Object value = results.get("login_attempts");
            if (value != null && !Data.isNull(value)) {
                if (value instanceof Long) {
                    user.login_attempts = (Long) value;
                } else if (value instanceof String) {
                    try { user.login_attempts = Long.parseLong((String) value); }
                    catch (NumberFormatException e) {
                        throw new ToznyApiException("While parsing login_attempts value: " + value, e);
                    }
                }
            }
        }

        tmp_block_timestamp:
        {
            Object value = results.get("tmp_block_timestamp");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                user.tmp_block_timestamp = (String) value;
            }
        }

        tmp_block_attempts:
        {
            Object value = results.get("tmp_block_timestamp");
            if (value != null && !Data.isNull(value) && (value instanceof Integer)) {
                user.tmp_block_attempts = (Integer) value;
            }
        }

        status:
        {
            Object value = results.get("status");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                user.status = (String) value;
            }
        }

        created:
        {
            Object value = results.get("created");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                try { user.created = new Date(Long.parseLong((String) value) * 1000); }
                catch (NumberFormatException e) {
                    throw new ToznyApiException("While parsing created value: " + value, e);
                }
            }
        }

        modified:
        {
            Object value = results.get("modified");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                try { user.modified = new Date(Long.parseLong((String) value) * 1000); }
                catch (NumberFormatException e) {
                    throw new ToznyApiException("While parsing modified value: " + value, e);
                }
            }
        }

        last_login:
        {
            Object value = results.get("last_login");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                try { user.last_login = new Date(Long.parseLong((String) value) * 1000); }
                catch (NumberFormatException e) {
                    throw new ToznyApiException("While parsing last_login value: " + value, e);
                }
            }
        }

        total_logins:
        {
            Object value = results.get("total_logins");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                try { user.total_logins = Long.parseLong((String) value); }
                catch (NumberFormatException e) {
                    throw new ToznyApiException("While parsing total_logins value: " + value, e);
                }
            }
        }

        total_failed_logins:
        {
            Object value = results.get("total_failed_logins");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                try { user.total_failed_logins = Long.parseLong((String) value); }
                catch (NumberFormatException e) {
                    throw new ToznyApiException("While parsing total_failed_logins value: " + value, e);
                }
            }
        }

        last_failed_login:
        {
            Object value = results.get("last_failed_login");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                try { user.last_failed_login = new Date(Long.parseLong((String) value) * 1000); }
                catch (NumberFormatException e) {
                    throw new ToznyApiException("While parsing last_failed_login value: " + value, e);
                }
            }
        }

        total_devices:
        {
            Object value = results.get("total_devices");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                try { user.total_devices = Integer.parseInt((String) value); }
                catch (NumberFormatException e) {
                    throw new ToznyApiException("While parsing total_devices value: " + value, e);
                }
            }
        }

        tozny_primary:
        {
            Object value = results.get("tozny_primary");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                user.tozny_primary = (String) value;
            }
        }

        tozny_secondary:
        {
            Object value = results.get("tozny_secondary");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                user.tozny_secondary = (String) value;
            }
        }

        tozny_username:
        {
            Object value = results.get("tozny_username");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                user.tozny_username = (String) value;
            }
        }

        tozny_email:
        {
            Object value = results.get("tozny_email");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                user.tozny_email = (String) value;
            }
        }

        meta:
        {
            Object value = results.get("meta");
            if (value != null && !Data.isNull(value) && (value instanceof Map)) {
                Map<String,String> map = new HashMap<String, String>();
                for (Entry<String,Object> e : ((Map<String,Object>)value).entrySet()) {
                    if (e.getValue() != null) map.put(e.getKey(), e.getValue().toString());
                }
                user.meta = map;
            }
        }

        return user;
    }
}



