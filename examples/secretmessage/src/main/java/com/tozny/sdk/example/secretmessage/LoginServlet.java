/*
 * LoginServlet.java
 *
 * Copyright (C) 2016, Tozny, LLC.
 * All Rights Reserved.
 *
 * Released under the Apache license. See the file "LICENSE"
 * for more information.
 */

package com.tozny.sdk.example.secretmessage;

import java.io.*;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.google.api.client.util.Base64;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.tozny.sdk.RealmApi;
import com.tozny.sdk.realm.RealmConfig;
import com.tozny.sdk.realm.config.ToznyRealmKeyId;
import com.tozny.sdk.realm.config.ToznyRealmSecret;

/**
 * Receive signed data from the Tozny Javascript and log the user in if
 * the authentication is successful.
 *
 * The Javascript frontend is configured to POST signed data to this URL
 * when the user authenticates via their mobile device. If the signature
 * is valid for our realm, we save the user's information to the session
 * and allow them to access protected resources.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private RealmApi realmApi;

    @Override
    public void init() throws ServletException {
        try {
            // Load realm configuration from a properties file.
            Properties prop = new Properties();
            prop.load(getServletContext().getResourceAsStream("/WEB-INF/properties/tozny.properties"));
            ToznyRealmKeyId realmKey = new ToznyRealmKeyId(prop.getProperty("realmKey"));
            ToznyRealmSecret realmSecret = new ToznyRealmSecret(prop.getProperty("realmSecret"));
            RealmConfig realmConfig = new RealmConfig(realmKey, realmSecret);
            this.realmApi = new RealmApi(realmConfig);
        } catch (Exception e) {
            throw new ServletException("Unable to configure login servlet", e);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String signature = req.getParameter("tozny_signature");
        String signedData = req.getParameter("tozny_signed_data");
        String path = req.getContextPath();

        if (signature == null || signedData == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (realmApi.verifyLogin(signedData, signature)) {
            String userInfoJson = new String(Base64.decodeBase64(signedData));
            ObjectMapper mapper = new ObjectMapper();
            UserInfo userInfo = mapper.readValue(userInfoJson, UserInfo.class);
            HttpSession session = req.getSession();
            session.setAttribute("userInfo", userInfo);
            resp.sendRedirect(path + "/protected/secretmessage.jsp");
        } else {
            resp.sendRedirect(path + "/index.jsp?error=Invalid+login");
            return;
        }
    }
}
