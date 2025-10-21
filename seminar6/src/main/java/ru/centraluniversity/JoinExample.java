package ru.centraluniversity;

public class JoinExample {
    static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> value = 42);
        t.start();
        t.join(); // гарантирует happens-before
        System.out.println(value); // всегда 42
    }
}