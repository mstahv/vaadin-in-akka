package com.vaadin.vaadininmuija;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import com.vaadin.vaadininmuija.akka.StockHubActor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet, that also initiates and holds references to ActorSystem and the stock
 * watch hub actor.
 */
@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = StockUI.class, widgetset = "com.vaadin.vaadininmuija.AppWidgetSet")
public class StockServlet extends VaadinServlet {

    private ActorSystem system;
    private ActorRef stockWatchHub;

    public ActorRef getStocksWatch() {
        return stockWatchHub;
    }

    public ActorSystem getSystem() {
        return system;
    }

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        system = ActorSystem.create("Stocks");
        stockWatchHub = system.actorOf(Props.create(StockHubActor.class));
    }

    @Override
    public void destroy() {
        system.shutdown();
        super.destroy();
    }

}
