package com.tozny.sdk.realm.methods.user_exists;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Key;
import com.tozny.sdk.realm.RealmConfig;
import com.tozny.sdk.realm.RealmUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Constructs a URL for invoking the "realm.user_exists" method.
 */
public class UserExistsUrl extends RealmUrl {

    public UserExistsUrl(RealmConfig config, JsonFactory jsonFactory) {
        super(config,jsonFactory,"realm.user_exists");
    }

    @Key
    public String user_id;

    @Key
    public String tozny_email;

    @Override
    protected Map<String, String> realmRequestParameters() {
        Map<String, String> map = new HashMap<String, String>();
        if (user_id != null) map.put("user_id", user_id);
        else if (tozny_email != null) map.put("tozny_email", tozny_email);
        return map;
    }
}
