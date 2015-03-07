package com.tozny.sdk.realm.methods.check_valid_login;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Key;
import com.tozny.sdk.realm.RealmConfig;
import com.tozny.sdk.realm.RealmUrl;

import java.util.HashMap;
import java.util.Map;

public class CheckValidLoginUrl extends RealmUrl {

    public CheckValidLoginUrl(RealmConfig config, JsonFactory jsonFactory, String user_id, String session_id) {
        super(config,jsonFactory,"realm.check_valid_login");
        this.user_id = user_id;
        this.session_id = session_id;
    }

    @Key
    public final String user_id;

    @Key
    public final String session_id;

    @Override
    protected Map<String, String> realmRequestParameters() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", user_id);
        map.put("session_id", session_id);
        return map;
    }
}