/*
 * Application.java
 *
 * Copyright (C) 2016, Tozny, LLC.
 * All Rights Reserved.
 *
 * Released under the Apache license. See the file "LICENSE"
 * for more information.
 */

package com.tozny.sdk.example.secretmessage;

import com.tozny.sdk.RealmApi;
import com.tozny.sdk.realm.RealmConfig;
import com.tozny.sdk.realm.config.ToznyRealmKeyId;
import com.tozny.sdk.realm.config.ToznyRealmSecret;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.mustache.MustacheMvcFeature;

public class Application extends ResourceConfig {

    public Application(@Context ServletContext context) throws IOException {
        setupTemplateEngine(context, "templates");
        RealmConfig realmConfig = getRealmConfig(context);
        RealmApi realmApi = new RealmApi(realmConfig);
        String contextPath = context.getContextPath();

        register(new SessionResource(contextPath, realmApi));
        register(new PublicResource(contextPath, realmConfig.realmKeyId));
        register(new ProtectedResource(contextPath));
    }

    private RealmConfig getRealmConfig(ServletContext context) throws IOException {
        // Load realm configuration from a properties file.
        Properties prop = new Properties();
        InputStream in = context.getResourceAsStream("/WEB-INF/properties/tozny.properties");
        if (in == null) {
            throw new IOException("property file not found: /WEB-INF/properties/tozny.properties");
        }
        try {
            prop.load(in);
        }
        finally {
            if (in != null) {
                in.close();
            }
        }
        ToznyRealmKeyId realmKey = new ToznyRealmKeyId(prop.getProperty("realmKey"));
        ToznyRealmSecret realmSecret = new ToznyRealmSecret(prop.getProperty("realmSecret"));
        return new RealmConfig(realmKey, realmSecret);
    }

    private void setupTemplateEngine(ServletContext context, String basePath) {
        property(MvcFeature.TEMPLATE_BASE_PATH, context.getRealPath(basePath));
        register(MustacheMvcFeature.class);
    }

}
