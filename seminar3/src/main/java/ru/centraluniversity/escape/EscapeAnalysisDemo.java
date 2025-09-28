package ru.centraluniversity.escape;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public class EscapeAnalysisDemo {

    public static void main(String[] args) {
        Random random = new Random();
        AtomicLong total = new AtomicLong();
        LongStream.generate(random::nextLong)
            .forEach(value ->
                {
                    total.addAndGet(computeDistance(value, value * 2));
                    if (value % 100000 == 0) {
                        System.out.println(total);
                    }
                }
            );

        System.out.println("Total: " + total);
    }

    public static long computeDistance(long a, long b) {
        // Point не "убегает" за пределы метода
        Point p = new Point(a, b);

        // Используем только getX() и getY()
        return Math.abs(p.getX() - p.getY());
    }
}
