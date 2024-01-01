/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.plugins.tomcat.test;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;
import com.lealone.main.Lealone;

public class TomcatServerStart {

    public static void main(String[] args) {
        Lealone.main(args);
        // startTomcat();
    }

    // http://localhost:8080/index.html
    public static void startTomcat() {
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(8080);
            tomcat.getConnector();
            Context ctx = tomcat.addContext("", new File("./src/test/resources/web").getCanonicalPath());
            Tomcat.addServlet(ctx, "defaultServlet", new DefaultServlet());
            ctx.addServletMappingDecoded("/", "defaultServlet");
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
