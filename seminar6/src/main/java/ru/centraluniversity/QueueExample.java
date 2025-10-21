package ru.centraluniversity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> q = new LinkedBlockingQueue<>();

        Thread producer = new Thread(() -> q.add(42));
        Thread consumer = new Thread(() -> {
            try {
                System.out.println(q.take()); // всегда 42
            } catch (InterruptedException e) { e.printStackTrace(); }
        });

        producer.start(); consumer.start();
        producer.join(); consumer.join();
    }
}