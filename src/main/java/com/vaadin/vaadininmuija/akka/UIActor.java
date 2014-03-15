package com.vaadin.vaadininmuija.akka;

import com.vaadin.vaadininmuija.akka.messages.StockHistory;
import com.vaadin.vaadininmuija.akka.messages.StockUpdate;
import com.vaadin.vaadininmuija.akka.messages.UnwatchStock;
import akka.actor.UntypedActor;
import com.vaadin.vaadininmuija.StockUI;

/**
 * The broker between the VaadinUI and the StockActor(s). The UserActor holds
 * the reference to VaadinUI and sends serialized JSON data to the client.
 * <p>
 * Note, In a more modular real world solution, this should be in the actual Vaadin 
 * app as where other actors should form their own independent module.
 * 
 */
public class UIActor extends UntypedActor {
    
    private final StockUI vaadinUi;

    public UIActor(StockUI ui) {
        this.vaadinUi = ui;
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof StockUpdate) {
            // push the stock to the client
            StockUpdate update = (StockUpdate) message;
            vaadinUi.updateStockDetails(update.getSymbol(), update.getPrice());
        } else if (message instanceof StockHistory) {
            // push initial stock details to the client
            StockHistory update = (StockHistory) message;
            vaadinUi.updateStockDetails(update.getSymbol(), update.getHistory());
        } else {
            unhandled(message);
        }
    }

    @Override
    public void postStop() throws Exception {
        vaadinUi.getHub().tell(new UnwatchStock(), getSender());
        super.postStop();
    }
    
}
