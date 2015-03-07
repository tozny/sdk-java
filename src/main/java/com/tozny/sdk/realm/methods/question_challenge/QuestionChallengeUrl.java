package com.tozny.sdk.realm.methods.question_challenge;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Key;
import com.tozny.sdk.realm.RealmConfig;
import com.tozny.sdk.realm.RealmUrl;

import java.util.HashMap;
import java.util.Map;

/**
 * Constructs a URL for invoking the "realm.question_challenge" method.
 */
public class QuestionChallengeUrl extends RealmUrl {

    public QuestionChallengeUrl(RealmConfig config, JsonFactory jsonFactory, String question) {
        super(config,jsonFactory,"realm.question_challenge");
        this.question = question;
    }

    @Key
    public String user_id;

    @Key
    public String question;

    @Override
    protected Map<String, String> realmRequestParameters() {
        Map<String, String> map = new HashMap<String, String>();
        if (user_id != null) map.put("user_id", user_id);
        map.put("question", question);
        return map;
    }
}