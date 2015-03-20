package com.vaadin.vaadininmuija.akka.messages;

import java.time.LocalDateTime;
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
        final LocalDateTime date = LocalDateTime.now().minusSeconds(secondDelta);
        return new StockQuote(price, date);
    }

    public StockQuote create() {
        return create(0);
    }

}
