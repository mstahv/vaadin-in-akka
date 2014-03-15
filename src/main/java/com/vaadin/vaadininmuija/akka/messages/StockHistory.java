package com.vaadin.vaadininmuija.akka.messages;

import java.util.List;

public class StockHistory extends AbstractStockMessage {

    private final StockQuote[] history;

    public StockHistory(String symbol, List<StockQuote> history) {
        super(symbol);
        this.history = history.toArray(new StockQuote[history.size()]);
    }

    public StockQuote[] getHistory() {
        return history;
    }

}
