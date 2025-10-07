package ru.centraluniversity.jit.inline;

public class InlineExample {

    public static int f(int x) {
        return x + 1;
    }

    public static int computeBad() {
        int sum = 0;
        for (int i = 0; i < 1_000_000; i++) sum += f(i);
        return sum;
    }

    public static int computeGood() {
        int sum = 0;
        for (int i = 0; i < 1_000_000; i++) sum += i + 1;
        return sum;
    }
}