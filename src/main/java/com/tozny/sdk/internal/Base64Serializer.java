package com.tozny.sdk.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Data submitted to the Tozny API is serialized to JSON, and is then
 * base64-encoded for URL-safety. This serializer automates this form of
 * serialization.
 */
public class Base64Serializer extends JsonSerializer<Object> {

    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] json = mapper.writeValueAsBytes(value);
        gen.writeString(ProtocolHelpers.base64UrlEncode(json));
    }

}
