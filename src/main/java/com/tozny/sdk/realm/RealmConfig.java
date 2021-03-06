package com.tozny.sdk.realm;

import com.tozny.sdk.realm.config.ToznyRealmKeyId;
import com.tozny.sdk.realm.config.ToznyRealmSecret;

/**
 * A simple bean for holding a realm's configuration.
 * You can obtain your realm's key and secret from the Tozny Admin Console (https://admin.tozny.com)
 * For more information on Tozny realm key managment, please see: (http://tozny.com/documentation/admin-console/key-management/)
 */
public class RealmConfig {

    public static final String TOZNY_PRODUCTION_API_URL = "https://api.tozny.com/";

    public final String apiUrl;
    public final ToznyRealmKeyId realmKeyId;
    public final ToznyRealmSecret realmSecret;

    /**
     * Constructs a RealmConfig instance using the given ToznyRealmKeyId
     * and ToznyRealmSecret instances with the production Tozny API urls.
     *
     * @param realmKeyId key ID of realm to authenticate to
     * @param realmSecret secret of realm to authenticate to
     */
    public RealmConfig(ToznyRealmKeyId realmKeyId, ToznyRealmSecret realmSecret) {
        this(TOZNY_PRODUCTION_API_URL, realmKeyId, realmSecret);
    }

    /**
     * Constructs a RealmConfig instance using the given ToznyRealmKeyId
     * and ToznyRealmSecret instances with the given Tozny API url
     * (useful for testing against Tozny's development environments).
     *
     * @param apiUrl Tozny API URL, default: <pre>https://api.tozny.com</pre>
     * @param realmKeyId key ID of realm to authenticate to
     * @param realmSecret secret of realm to authenticate to
     */
    public RealmConfig(String apiUrl, ToznyRealmKeyId realmKeyId,  ToznyRealmSecret realmSecret) {
        this.apiUrl = apiUrl;
        this.realmKeyId = realmKeyId;
        this.realmSecret = realmSecret;
    }

    @Override
    public String toString() {
        return "RealmConfig{" +
                "apiUrl='" + apiUrl + "'" +
                ", realmKeyId=" + realmKeyId +
                ", realmSecret=" + realmSecret +
                '}';
    }
}
