package ru.centraluniversity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Этот класс демонстрирует использование атомарных операций,
 * которые обеспечивают отношение happens-before между потоками.
 * Благодаря этому, изменения, сделанные одним потоком,
 * становятся видимыми другим потокам в предсказуемом порядке,
 * что гарантирует корректность результата без использования synchronized.
 */

public class AtomicExample {
    static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++)
                counter.incrementAndGet(); // атомарно и с happens-before
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++)
                counter.incrementAndGet();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Counter = " + counter.get()); // всегда 2000
    }
}