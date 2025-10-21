package ru.centraluniversity;

public class VolatileCounter {
    private static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> { for(int i=0;i<100000;i++) count++; });
        Thread t2 = new Thread(() -> { for(int i=0;i<100000;i++) count++; });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println(count); // может быть < 2000
    }
}