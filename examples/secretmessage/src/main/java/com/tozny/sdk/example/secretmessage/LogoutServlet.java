/*
 * LogoutServlet.java
 *
 * Copyright (C) 2016, Tozny, LLC.
 * All Rights Reserved.
 *
 * Released under the Apache license. See the file "LICENSE"
 * for more information.
 */

package com.tozny.sdk.example.secretmessage;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        String path = req.getContextPath();
        resp.sendRedirect(path + "/index.jsp");
    }
}
