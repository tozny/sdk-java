package com.tozny.sdk.realm.config;

public class ToznyRealmKeyId {
    public final String value;

    public ToznyRealmKeyId(String realmKeyId) {
        this.value = realmKeyId;
    }

    @Override
    public String toString() { return value; }
}
