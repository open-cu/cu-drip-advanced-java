package ru.centraluniversity;

import java.util.concurrent.atomic.AtomicBoolean;

public class VisibilityProblem {
    private static AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (running.get()) {
                // ...
            }
            System.out.println("Thread stopped");
        });

        t.start();
        Thread.sleep(100);
        running.set(false); // поток может не увидеть это изменение!
        t.join();
    }
}
