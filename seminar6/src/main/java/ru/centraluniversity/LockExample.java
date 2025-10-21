package ru.centraluniversity;

import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    static int data = 0;
    static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread writer = new Thread(() -> {
            lock.lock();
            try {
                data = 100;
            } finally {
                lock.unlock();
            }
        });

        Thread reader = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(data);
            } finally {
                lock.unlock();
            }
        });

        writer.start();
        writer.join();
        reader.start();
        reader.join();
    }
}