package com.tozny.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A base interface for Realm and User API requests to extend.
 * All Tozny API calls have a required 'method' property.
 */
public interface ToznyApiRequest {

    public String getMethod();

}
