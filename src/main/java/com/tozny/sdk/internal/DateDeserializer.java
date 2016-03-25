package com.tozny.sdk.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

public class DateDeserializer extends JsonDeserializer<Date> {

    public Date deserialize(JsonParser jp, DeserializationContext context)
        throws IOException, JsonProcessingException {
        long timestamp;
        JsonToken token = jp.getCurrentToken();
        if (token.equals(JsonToken.VALUE_NUMBER_INT)) {
            timestamp = jp.getLongValue();
        }
        else if (token.equals(JsonToken.VALUE_STRING)) {
            String value = jp.getText();
            try {
                timestamp = Long.parseLong(value);
            }
            catch (NumberFormatException e) {
                throw context.instantiationException(Long.class, e);
            }
        }
        else {
            throw context.wrongTokenException(jp, token, "While parsing Date value, expected a number or a string containing a number.");
        }
        return new Date(timestamp * 1000);
    }

}
