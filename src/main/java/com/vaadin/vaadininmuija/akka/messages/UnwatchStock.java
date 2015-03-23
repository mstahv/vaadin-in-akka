package com.vaadin.vaadininmuija.akka.messages;

public class UnwatchStock extends AbstractStockMessage {
	private static final long serialVersionUID = 5245224714672940084L;

	public UnwatchStock(String stock) {
        super(stock);
    }

    public UnwatchStock() {
        super(null);
    }

}
