package com.tozny.sdk.realm.methods.question_challenge;

import com.google.api.client.util.Data;
import com.tozny.sdk.ToznyApiException;
import com.tozny.sdk.ToznyApiResponse;
import com.tozny.sdk.realm.Session;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;


/**
 * A GenericJson instance intended for marshaling an instance of Session.
 */
public class QuestionChallengeResponse extends ToznyApiResponse {

    public Session getChallengeSession() {

        Session session = new Session();

        challenge:
        {
            Object value = this.get("challenge");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                session.challenge = (String) value;
            }
        }

        realm_key_id:
        {
            Object value = this.get("realm_key_id");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                session.realm_key_id = (String) value;
            }
        }

        session_id:
        {
            Object value = this.get("session_id");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                session.session_id = (String) value;
            }
        }

        presence:
        {
            Object value = this.get("presence");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                session.presence = (String) value;
            }
        }

        qr_url:
        {
            Object value = this.get("qr_url");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                try {session.qr_url = new URL((String)value);}
                catch (MalformedURLException e) {
                    throw new ToznyApiException("While parsing qr_url value: " + value, e);
                }
            }
        }

        mobile_url:
        {
            Object value = this.get("mobile_url");
            if (value != null && !Data.isNull(value) && (value instanceof String)) {
                session.mobile_url = (String)value;
            }
        }


        created_at:
        {
            Object value = this.get("created_at");
            if (value != null && !Data.isNull(value) && (value instanceof Number)) {
                session.created_at = new Date(((Number) value).longValue() * 1000);
            }
            else if (value != null && !Data.isNull(value) && (value instanceof String)) {
                try { session.created_at = new Date(Long.parseLong((String) value) * 1000); }
                catch (NumberFormatException e) {
                    throw new ToznyApiException("While parsing created_at value: " + value, e);
                }
            }
        }

        return session;
    }
}
