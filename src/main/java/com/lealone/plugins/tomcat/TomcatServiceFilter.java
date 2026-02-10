/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.plugins.tomcat;

import java.io.IOException;

import com.lealone.plugins.service.ServiceHandler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TomcatServiceFilter extends HttpFilter {

    protected final ServiceHandler serviceHandler;

    public TomcatServiceFilter(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}
