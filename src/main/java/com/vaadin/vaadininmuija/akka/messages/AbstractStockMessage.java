package com.vaadin.vaadininmuija.akka.messages;

import java.io.Serializable;

/**
 * A superclass for stock messages.
 */
public abstract class AbstractStockMessage implements Serializable {

    private final String symbol;

    public AbstractStockMessage(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean hasSymbol() {
        return symbol != null;
    }

}
