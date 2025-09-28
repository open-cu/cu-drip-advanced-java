package ru.centraluniversity.pool;

import java.util.Random;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

public class EventConsumePool {

    public static void main(String[] args) {
        EventPool pool = new EventPool(100);
        Random random = new Random();
        IntStream.generate(random::nextInt)
            .boxed()
            .gather(Gatherers.windowFixed(3))
            .forEach(list -> {
                try {
                    var event = pool.borrowEvent();
                    event.reset();
                    event.setUserId(list.get(0));
                    event.setX(list.get(1));
                    event.setY(list.get(2));
                    processEvent(event);
                    pool.returnEvent(event);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    public static void processEvent(Event event) {
        if (event.getUserId() % 100000 == 0) {
            System.out.println(event);
        }
    }
}
