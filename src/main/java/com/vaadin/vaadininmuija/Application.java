package com.vaadin.vaadininmuija;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

/**
 * This class starts the web app via "main method". This is used by Typesafe
 * Activator and can be launched with "sbt run *". Within std IDE, use jetty
 * maven plugin or IDE server support to deploy.
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
            WebAppContext webapp = new WebAppContext();
            webapp.setContextPath("/");
            webapp.setBaseResource(Resource.newResource(new File(
                    "target/classes/webapp")));
            webapp.addServlet(StockServlet.class, "/*");
            webapp.setConfigurations(
                    new Configuration[]{new WebXmlConfiguration()});
            server.setHandler(webapp);
            server.start();
            Logger.getLogger(Application.class.getName()).
                    info("Server now running at http://localhost:8080/");
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

}
