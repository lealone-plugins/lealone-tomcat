/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package org.lealone.plugins.tomcat;

import org.lealone.plugins.service.http.HttpServerEngine;
import org.lealone.server.ProtocolServer;

public class TomcatServerEngine extends HttpServerEngine {

    public static final String NAME = "tomcat";

    public TomcatServerEngine() {
        super(NAME);
    }

    @Override
    protected ProtocolServer createProtocolServer() {
        return new TomcatServer();
    }
}
