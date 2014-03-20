package com.vaadin.vaadininmuija.akka.messages;

import java.util.Date;
import java.util.Random;

/**
 * Creates a randomly generated price based on the previous price
 */
public class FakeStockQuote {

    private final Random random = new Random();
    private Double lastPrice = 100 + random.nextDouble() * 50;

    public StockQuote create(int secondDelta) {
        // TODO improve the algorithm, this will slowly close zero when run some hours
        Double price = lastPrice = lastPrice * (0.95 + (0.1 * random.nextDouble()));
        // TODO this example should definitely use Java 8 dates :-)
        final Date date = new Date();
        date.setSeconds(date.getSeconds() - secondDelta);
        return new StockQuote(price, date);
    }

    public StockQuote create() {
        return create(0);
    }

}
