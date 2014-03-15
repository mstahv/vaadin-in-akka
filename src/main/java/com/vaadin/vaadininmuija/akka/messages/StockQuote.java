package com.vaadin.vaadininmuija.akka.messages;

import java.io.Serializable;
import java.util.Date;

public class StockQuote implements Serializable {
    private final Double price;
    private final Date timeStamp;

    public StockQuote(Double price, Date timeStamp) {
        this.price = price;
        this.timeStamp = timeStamp;
    }

    public Double getPrice() {
        return price;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
    
}
