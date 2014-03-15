package com.vaadin.vaadininmuija.akka.messages;

public class WatchStock extends AbstractStockMessage{

    public WatchStock(String stock) {
        super(stock);
    }
    
}
