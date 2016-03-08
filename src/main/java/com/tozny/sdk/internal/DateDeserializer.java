package com.tozny.sdk.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

public class DateDeserializer extends JsonDeserializer<Date> {

    public Date deserialize(JsonParser jp, DeserializationContext context)
        throws IOException, JsonProcessingException {
        String value = jp.nextTextValue();
        Date created = null;
        if (value != null) {
            try {
                created = new Date(Long.parseLong((String) value) * 1000);
            }
            catch (NumberFormatException e) {
                throw new JsonProcessingException();
                throw new ToznyApiException("While parsing created value: " + value, e);
            }
        }
        return created;
    }

}
