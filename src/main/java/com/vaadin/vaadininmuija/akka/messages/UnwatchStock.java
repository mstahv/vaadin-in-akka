package com.vaadin.vaadininmuija.akka.messages;

public class UnwatchStock extends AbstractStockMessage {

    public UnwatchStock(String stock) {
        super(stock);
    }

    public UnwatchStock() {
        super(null);
    }

}
