package ru.centraluniversity.tlab;

// -Xlog:gc+tlab=trace -Xmx10m -XX:+UseSerialGC
public class TLABMultiThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> allocate());
        Thread t2 = new Thread(() -> allocate());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    static void allocate() {
        var sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            var obj = new Object(); // Создаём много мелких объектов
            sum += obj.hashCode();
        }
        System.out.println(sum);
    }
}
