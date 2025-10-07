package ru.centraluniversity.jit.escape;

public class EscapeExample {

    static Point last;

    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    public static int compute() {
        Point p = new Point(10, 20);
        last = p;
        return p.x + p.y;
    }

}