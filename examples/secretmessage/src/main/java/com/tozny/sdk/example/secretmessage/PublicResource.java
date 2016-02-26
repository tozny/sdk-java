/*
 * PublicResource.java
 *
 * Copyright (C) 2016, Tozny, LLC.
 * All Rights Reserved.
 *
 * Released under the Apache license. See the file "LICENSE"
 * for more information.
 */

package com.tozny.sdk.example.secretmessage;

import com.tozny.sdk.realm.config.ToznyRealmKeyId;

import org.glassfish.jersey.server.mvc.Viewable;

import java.net.URI;
import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

@Path("/")
public class PublicResource {

    private final ToznyRealmKeyId realmKeyId;
    private final String contextPath;

    public PublicResource(String contextPath, ToznyRealmKeyId realmKeyId) {
        this.realmKeyId = realmKeyId;
        this.contextPath = contextPath;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable index(@QueryParam(value="error") final String error) {
        return new Viewable("/index", new Object() {
            public ToznyRealmKeyId getRealmKeyId() { return realmKeyId; }
            public List<String> getErrors() {
                List errors = new ArrayList();
                if (error != null) { errors.add(error); }
                return errors;
            }
            public URI getLoginUrl() { return base().path(SessionResource.class).build(); }
            public URI getLogoutUrl() { return base().path(SessionResource.class).build(); }
            public URI getIndexUrl() { return base().path(PublicResource.class).build(); }
            public URI getProtectedUrl() { return base().path(ProtectedResource.class).build(); }
        });
    }

    private UriBuilder base() {
        return UriBuilder.fromPath(contextPath);
    }

}
