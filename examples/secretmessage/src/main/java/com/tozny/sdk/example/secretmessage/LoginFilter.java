/*
 * LoginFilter.java
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
import javax.servlet.annotation.WebFilter;
import javax.ws.rs.core.UriBuilder;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest greq, ServletResponse gresp, FilterChain chain) throws ServletException, IOException{
        HttpServletRequest req = (HttpServletRequest) greq;
        HttpServletResponse resp = (HttpServletResponse) gresp;
        HttpSession session = req.getSession(false);

        if (session != null) {
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

            if (userInfo != null) {
                // User information is valid, continue to the page.
                chain.doFilter(greq, gresp);
                return;
            }
        }

        // User is not authenticated, redirect to login URL.
        resp.sendRedirect(
                UriBuilder
                .fromPath(req.getContextPath())
                .path(PublicResource.class)
                .queryParam("error", "Please log in")
                .build()
                .toString()
                );
    }
}
