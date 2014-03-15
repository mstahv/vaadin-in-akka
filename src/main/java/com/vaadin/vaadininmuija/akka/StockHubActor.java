package com.vaadin.vaadininmuija.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.vaadin.vaadininmuija.akka.messages.UnwatchStock;
import com.vaadin.vaadininmuija.akka.messages.WatchStock;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * This actor is a "hub" for StockActors that are created on demand, one per
 * symbol.
 *
 * This actor is build using the new "lambda style" API, introduced in Akka 2.3.
 * Using it one can get rid of typical instanceof checks and casts, that may
 * feel bit nasty in pre Java 8 era Akka code (unless using Scala). See
 * StockActor for "legacy" example.
 */
public class StockHubActor extends AbstractActor {

    @Override
    public PartialFunction<Object, BoxedUnit> receive() {
        return ReceiveBuilder.
                match(WatchStock.class, msg -> {
                    getOrCreateStockActor(msg).forward(msg, getContext());
                }).
                match(UnwatchStock.class, msg -> {
                    if (msg.hasSymbol()) {
                        getContext().getChild(msg.getSymbol()).forward(msg, getContext());
                    } else {
                        // Unwatch all if stock symbol is not provided
                        getContext().getChildren()
                                .forEach(ref -> ref.forward(msg, getContext()));
                    }
                }).build();
    }

    /**
     * Gets existing StockActor reference for given stock symbol or creates a
     * new.
     *
     * @param msg
     * @return
     */
    private ActorRef getOrCreateStockActor(WatchStock msg) {
        ActorRef child = getContext().getChild(msg.getSymbol());
        if (child == null) {
            child = getContext().actorOf(
                    Props.create(StockActor.class, msg.getSymbol()),
                    msg.getSymbol());
        }
        return child;
    }

}
