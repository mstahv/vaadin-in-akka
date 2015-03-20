package com.vaadin.vaadininmuija.akka.messages;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StockQuote implements Serializable {
	private static final long serialVersionUID = -3763258204335587693L;

	private final Double price;
    private final LocalDateTime timeStamp;

    public StockQuote(Double price, LocalDateTime date) {
        this.price = price;
        this.timeStamp = date;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    
}
