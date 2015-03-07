package com.tozny.sdk;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

/**
 * A parent class for Realm and User API urls to extend.
 * All Tozny API calls have a required 'method' property.
 */
public class ToznyApiUrl extends GenericUrl {
    public static final String TOZNY_PRODUCTION_API_URL = "https://api.tozny.com";

    public ToznyApiUrl(String toznyApiUrl, String method) {
        super(toznyApiUrl);
        this.method = method;
    }


    @Key
    public String method;
}
