package com.vaadin.vaadininmuija.akka.messages;

import java.util.List;

public class StockHistory extends AbstractStockMessage {

	private static final long serialVersionUID = 2858707450440055404L;
	private final StockQuote[] history;

    public StockHistory(String symbol, List<StockQuote> history) {
        super(symbol);
        this.history = history.toArray(new StockQuote[history.size()]);
    }

    public StockQuote[] getHistory() {
        return history;
    }

}
