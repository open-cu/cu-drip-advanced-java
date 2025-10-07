package ru.centraluniversity.jit.licm;

public class LICMExample {

    public static double sumBad(int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) sum += Math.sin(3.14);
        return sum;
    }

    public static double sumGood(int n) {
        double v = Math.sin(3.14);
        double sum = 0;
        for (int i = 0; i < n; i++) sum += v;
        return sum;
    }
}