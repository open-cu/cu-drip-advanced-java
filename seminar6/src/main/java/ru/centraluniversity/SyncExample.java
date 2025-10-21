package ru.centraluniversity;

public class SyncExample {
    static int shared = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread writer = new Thread(() -> {
            synchronized (SyncExample.class) {
                shared = 42;
            }
        });

        Thread reader = new Thread(() -> {
            synchronized (SyncExample.class) {
                System.out.println(shared);
            }
        });

        writer.start();
        writer.join();
        reader.start();
        reader.join();
    }
}