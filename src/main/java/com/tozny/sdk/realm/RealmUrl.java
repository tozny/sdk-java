package com.tozny.sdk.realm;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.tozny.sdk.Protocol;
import com.tozny.sdk.ToznyApiUrl;

import java.io.IOException;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * All Tozny API realm calls must be signed, using the a Realm Secret.
 * The Tozny API will expect a Realm call to consist of two paramaters: signature and signed_data.
 * RealmUrl extends GenericUrl via ToznyApiUrl, and overrides the entrySet() value to provide the
 * signing functions automatically to any descendants of RealmUrl (via realmRequestParameters).
 */
public abstract class RealmUrl extends ToznyApiUrl {
    private static final Logger LOG = Logger.getLogger(RealmUrl.class.getName());


    public final RealmConfig realmConfig;
    public final JsonFactory jsonFactory;

    protected RealmUrl(RealmConfig realmConfig, JsonFactory jsonFactory, String method) {
        super(realmConfig.apiUrl, method);
        this.realmConfig = realmConfig;
        this.jsonFactory = jsonFactory;
    }

    protected abstract Map<String,String> realmRequestParameters();




    // see: https://github.com/google/google-http-java-client/issues/26
    @Override public final Set<Entry<String, Object>> entrySet() {

        try {

            StringWriter out = new StringWriter();
            JsonGenerator jsonGenerator = this.jsonFactory.createJsonGenerator(out);

            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("method");
            jsonGenerator.writeString(this.method);
            jsonGenerator.writeFieldName("realm_key_id");
            jsonGenerator.writeString(this.realmConfig.realmKeyId.value);
            jsonGenerator.writeFieldName("nonce");
            jsonGenerator.writeString(Protocol.getNonce());
            jsonGenerator.writeFieldName("expires_at");
            jsonGenerator.writeString(Protocol.getExpires());

            for (Entry<String,String> e: realmRequestParameters().entrySet()) {
                jsonGenerator.writeFieldName(e.getKey());
                jsonGenerator.writeString(e.getValue());
            }

            jsonGenerator.writeEndObject();
            jsonGenerator.flush();
            jsonGenerator.close();

            String json = out.toString();
            String encoded = Protocol.base64UrlEncode(json.getBytes(Protocol.utf8));
            String signature = Protocol.sign(this.realmConfig.realmSecret, encoded);

            Map<String, Object> params  = new LinkedHashMap<String, Object>();
            params.put("signed_data", encoded);
            params.put("signature", signature);
            return params.entrySet();
        }
        catch (IOException e) {
            String message = "While serializing Realm paramaters to JSON.";
            LOG.log(Level.WARNING, message, e);
            throw new RuntimeException(message,e);
        } catch (NoSuchAlgorithmException e) {
            String message = "While initializing SecureRandom instance with "+Protocol.RANDOM_ALGORITHM+" algorithm.";
            LOG.log(Level.WARNING, message, e);
            throw new RuntimeException(message,e);
        } catch (InvalidKeyException e) {
            String message = "While signing payload.";
            LOG.log(Level.WARNING, message, e);
            throw new RuntimeException(message,e);
        }


    }
}
