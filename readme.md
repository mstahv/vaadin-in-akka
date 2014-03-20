# Vaadin( )in Akka

This is a full Java rewrite of the iconic "Reactive Stocks" Akka example app, with Vaadin UI. This project can act as an example how one can create a cool Vaadin RIA UI easily with plain Java - for your existing fault tolerant actor system built with [Akka](http://akka.io).

The project uses Java 8, so be sure to use that when trying this out. StockHubActor (StocksActor in original example) uses Akkas's [new Lambda optimized API](http://typesafe.com/blog/akka-230-major-release), introduced in 2.3. The StockActor is implemented in "legacy method" to demonstrate the difference of these two APIs.

The build uses currently Maven, so you can just import it to your favourite IDE and start playing with it. At least [NetBeans 8](https://netbeans.org) works out of the box with its Java 8 support. Also, if you just wish to see how the app looks, you can use `mvn package jetty:run` to launch the app locally from command line.

Note for Scala fanatics: You can rewrite any part of the application with Scala as well, and still use nice combination of Akka and Vaadin. Mixing them works fine e.g. with [maven-scala-plugin](http://scala-tools.org/mvnsites/maven-scala-plugin/) or using the sbt build. And I'm sure you can also save some lines of code too ;-)

### Building/running with Maven

mvn package jetty:run

### SBT

Running Jetty

    sbt container:start ~aux-compile
    
Generating Eclipse configurations:
    
    sbt eclipse

###TODO:

 * Use Java 8 date types in StockQuote
 * Consider using Spring or Vaadin CDI to inject system & stocks hub reference
 * Consider adding similar "twitter AI" to suggest buy/hold/sell as in original example. That would make this example a "somelainen muija", Sorry, can't translate :-)
