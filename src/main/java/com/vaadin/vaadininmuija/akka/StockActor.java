package com.vaadin.vaadininmuija.akka;

import com.vaadin.vaadininmuija.akka.messages.StockQuote;
import com.vaadin.vaadininmuija.akka.messages.FakeStockQuote;
import com.vaadin.vaadininmuija.akka.messages.StockHistory;
import com.vaadin.vaadininmuija.akka.messages.StockUpdate;
import com.vaadin.vaadininmuija.akka.messages.UnwatchStock;
import com.vaadin.vaadininmuija.akka.messages.WatchStock;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.UntypedActor;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.FiniteDuration;

/**
 * These actors are instantiated by the hub, one per stock symbol, and they
 * generate some fake data for stock values.
 */
public class StockActor extends UntypedActor {

    private static final int HISTORY_SIZE = 50;

    private final FakeStockQuote fakeStockQuote = new FakeStockQuote();
    private final LinkedList<StockQuote> history = new LinkedList<>();
    private final Set<ActorRef> watchers = new HashSet<>();
    private final String symbol;
    private Cancellable stockTick;
    private final Random random = new Random();

    public StockActor(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof FetchLatest) {
            // react randomly on every third sheduling to make data more
            // realistic
            if (random.nextInt(3) == 0) {
                // add a new stock price to the history and drop the oldest
                StockQuote newPrice = fakeStockQuote.create();
                history.add(newPrice);
                history.remove();
                for (ActorRef r : watchers) {
                    r.tell(new StockUpdate(symbol, newPrice), getSelf());
                }
            }
        } else if (message instanceof WatchStock) {
            ensureDemoDataGeneration();
            // add to watchers...
            watchers.add(getSender());
            // ... and tell the last updates
            getSender().tell(new StockHistory(symbol, history), getSelf());
        } else if (message instanceof UnwatchStock) {
            watchers.remove(getSender());
            if (watchers.isEmpty()) {
                // TODO original example stps here, but instead should somehow
                // lazily stop the actor, if nobody reconnects in a minute.
                // With this toggling shows random values
                // getContext().stop(getSelf());
                // stockTick.cancel();
            }
        } else {
            unhandled(message);
        }
    }

    private void ensureDemoDataGeneration() {

        if (stockTick == null || stockTick.isCancelled()) {
            // create some demo data for initial history
            if (history.isEmpty()) {
                for (int i = 0; i < HISTORY_SIZE; i++) {
                    history.add(fakeStockQuote.create((HISTORY_SIZE - i) * 3));
                }
            }

            // Schedule stock updates
            stockTick = getContext().system().scheduler().schedule(FiniteDuration.Zero(),
                    FiniteDuration.create(1000, TimeUnit.MILLISECONDS), () -> {
                        self().tell(new FetchLatest(), self());
                    }, getContext().dispatcher());
        }
    }

    /**
     * Simple internally used message to schedule demo data generation
     */
    public static class FetchLatest implements Serializable {

    }

}
