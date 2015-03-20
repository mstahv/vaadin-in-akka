package com.vaadin.vaadininmuija.akka.messages;

public class StockUpdate extends AbstractStockMessage {
	private static final long serialVersionUID = 8480374648195455694L;
	private final StockQuote price;

    public StockUpdate(String symbol, StockQuote price) {
        super(symbol);
        this.price = price;
    }

    public StockQuote getPrice() {
        return price;
    }
    
    
    
}
