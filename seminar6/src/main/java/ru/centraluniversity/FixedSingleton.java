package ru.centraluniversity;

public class FixedSingleton {
    private static volatile FixedSingleton instance; // <-- volatile

    private int value;

    private FixedSingleton() {
        value = 42;
        try { Thread.sleep(1); } catch (InterruptedException ignored) {}
    }

    public static FixedSingleton getInstance() {
        if (instance == null) {
            synchronized (FixedSingleton.class) {
                if (instance == null) {
                    instance = new FixedSingleton();
                }
            }
        }
        return instance;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1_000_000; i++) {
            Thread t1 = new Thread(FixedSingleton::getInstance);
            Thread t2 = new Thread(FixedSingleton::getInstance);

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            FixedSingleton obj = FixedSingleton.getInstance();
            if (obj.getValue() != 42) {
                System.out.println("Singleton broken! value = " + obj.getValue());
                break;
            }
        }
        System.out.println("Finished test");
    }
}
