package com.tozny.sdk.realm.methods.question_challenge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiRequest;

import javax.annotation.Nullable;

/**
 * Constructs a Request for invoking the "realm.question_challenge" method.
 */
public class QuestionChallengeRequest implements ToznyApiRequest {

    @JsonProperty(required=true) private String method = "realm.question_challenge";
    @JsonProperty(required=true) private String question;
    @JsonProperty private String user_id;

    public QuestionChallengeRequest(String question, @Nullable String user_id) {
        this.question = question;
        this.user_id = user_id;
    }

    public String getMethod() {
        return method;
    }

    public String getQuestion() {
        return question;
    }

    @Nullable
    public String getUserId() {
        return user_id;
    }

}
