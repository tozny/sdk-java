/*
 * SessionResource.java
 *
 * Copyright (C) 2016, Tozny, LLC.
 * All Rights Reserved.
 *
 * Released under the Apache license. See the file "LICENSE"
 * for more information.
 */

package com.tozny.sdk.example.secretmessage;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tozny.sdk.RealmApi;
import com.tozny.sdk.internal.ProtocolHelpers;
import com.tozny.sdk.realm.RealmConfig;
import com.tozny.sdk.realm.config.ToznyRealmKeyId;
import com.tozny.sdk.realm.config.ToznyRealmSecret;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * Receive signed data from the Tozny Javascript and log the user in if
 * the authentication is successful.
 *
 * The Javascript frontend is configured to POST signed data to this URL
 * when the user authenticates via their mobile device. If the signature
 * is valid for our realm, we save the user's information to the session
 * and allow them to access protected resources.
 */
@Path("/session")
public class SessionResource {

    private final RealmApi realmApi;
    private final String contextPath;

    public SessionResource(String contextPath, RealmApi realmApi) {
        this.realmApi = realmApi;
        this.contextPath = contextPath;
    }

    /*
     * POSTing a valid Tozny assertion creates an authenticated session.
     * This is how a user logs in.
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createSession(
            @FormParam("signed_data") String signedData,
            @FormParam("signature")   String signature,
            @Context HttpServletRequest req
            ) {
        if (signature == null || signedData == null) {
            return Response.status(badRequest()).build();
        }

        if (realmApi.verifyLogin(signedData, signature)) {
            UserInfo userInfo;
            try {
                userInfo = parseSignedData(signedData, UserInfo.class);
            }
            catch (IOException e) {
                return Response.serverError().entity(e).build();
            }

            HttpSession session = req.getSession();
            session.setAttribute("userInfo", userInfo);
            return Response
                .created(
                        UriBuilder
                        .fromPath(contextPath)
                        .path(getClass())
                        .build())
                .build();
        } else {
            return notAuthorized();
        }
    }

    /*
     * DELETEing destroys the user's authenticated session - if one is active.
     * This is how the user logs out.
     */
    @DELETE
    public Response destroySession(@Context HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return Response.noContent().build();
    }

    private <T> T parseSignedData(String signedData, Class<T> klass) throws IOException {
        String json = new String(ProtocolHelpers.base64UrlDecode(signedData));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, klass);
    }

    private Response.StatusType badRequest() {
        return new Response.StatusType() {
            public Response.Status.Family getFamily() {
                return Response.Status.Family.CLIENT_ERROR;
            }
            public String getReasonPhrase() { return "400 Bad Request"; }
            public int getStatusCode() { return 400; }
        };
    }

    private Response notAuthorized() {
        return Response
            .status(Response.Status.UNAUTHORIZED)
            .build();
    }

}

