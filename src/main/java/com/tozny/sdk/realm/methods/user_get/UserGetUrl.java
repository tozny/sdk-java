package com.tozny.sdk.realm.methods.user_get;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Key;
import com.tozny.sdk.realm.RealmConfig;
import com.tozny.sdk.realm.RealmUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Constructs a URL for invoking the "realm.user_get" method.
 */
public class UserGetUrl extends RealmUrl {

    public UserGetUrl(RealmConfig config, JsonFactory jsonFactory) {
        super(config,jsonFactory,"realm.user_get");
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
