package ru.centraluniversity.pool;

import java.util.Random;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

public class EventConsume {

    public static void main(String[] args) {
        Random random = new Random();
        IntStream.generate(random::nextInt)
            .boxed()
            .gather(Gatherers.windowFixed(3))
            .forEach(list -> processEvent(
                new Event(
                    list.get(0),
                    list.get(1),
                    list.get(2)
                )
            ));
    }

    public static void processEvent(Event event) {
        if (event.getUserId() % 100000 == 0) {
            System.out.println(event);
        }
    }
}
