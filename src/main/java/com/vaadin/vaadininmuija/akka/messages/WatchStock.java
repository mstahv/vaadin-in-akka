package com.vaadin.vaadininmuija.akka.messages;

public class WatchStock extends AbstractStockMessage{
	private static final long serialVersionUID = 4910131873637730093L;

	public WatchStock(String stock) {
        super(stock);
    }
    
}
