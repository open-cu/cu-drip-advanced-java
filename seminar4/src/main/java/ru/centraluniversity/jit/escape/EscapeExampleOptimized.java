package ru.centraluniversity.jit.escape;

public class EscapeExampleOptimized {
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    public static int compute() {
        EscapeExample.Point p = new EscapeExample.Point(10, 20);
        return p.x + p.y;
    }

}