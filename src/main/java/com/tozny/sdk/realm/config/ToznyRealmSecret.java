package com.tozny.sdk.realm.config;


public class ToznyRealmSecret {
    public final String value;

    public ToznyRealmSecret (String realmSecret) {
        this.value = realmSecret;
    }

    @Override
    public String toString() { return value; }
}
