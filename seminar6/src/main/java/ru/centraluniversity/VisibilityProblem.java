package ru.centraluniversity;

public class VisibilityProblem {
    private static boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (running) {
                // ...
            }
            System.out.println("Thread stopped");
        });

        t.start();
        Thread.sleep(100);
        running = false; // поток может не увидеть это изменение!
        t.join();
    }
}