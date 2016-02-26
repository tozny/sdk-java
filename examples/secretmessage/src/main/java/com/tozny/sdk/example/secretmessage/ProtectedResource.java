/*
 * ProtectedResource.java
 *
 * Copyright (C) 2016, Tozny, LLC.
 * All Rights Reserved.
 *
 * Released under the Apache license. See the file "LICENSE"
 * for more information.
 */

package com.tozny.sdk.example.secretmessage;

import org.glassfish.jersey.server.mvc.Viewable;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

@Path("/protected")
public class ProtectedResource {

    private final String contextPath;

    public ProtectedResource(String contextPath) {
        this.contextPath = contextPath;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable index() {
        return new Viewable("/secretmessage", new Object() {
            public URI getLoginUrl() { return base().path(SessionResource.class).build(); }
            public URI getLogoutUrl() { return base().path(SessionResource.class).build(); }
            public URI getIndexUrl() { return base().path(PublicResource.class).build(); }
        });
    }

    private UriBuilder base() {
        return UriBuilder.fromPath(contextPath);
    }

}
