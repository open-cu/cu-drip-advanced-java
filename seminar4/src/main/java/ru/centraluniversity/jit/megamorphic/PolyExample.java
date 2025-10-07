package ru.centraluniversity.jit.megamorphic;

public class PolyExample {

    public static int runMega() {
        int sum = 0;
        Op[] ops = {new Add(), new Mul(), new Sub()};
        for (int i = 0; i < 1000; i++) for (Op o : ops) sum += o.apply(i);
        return sum;
    }

    public static int runMono() {
        int sum = 0;
        Op[] ops = {new Add(), new Add(), new Add()};
        for (int i = 0; i < 1000; i++) for (Op o : ops) sum += o.apply(i);
        return sum;
    }
}
