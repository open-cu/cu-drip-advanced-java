package ru.centraluniversity;

public class Main {

    public static void main(String[] args) {
        System.out.println(fastInvSqrt(123.45));
        System.out.println(1 / Math.sqrt(123.45));
        System.out.println(Math.pow(Math.sqrt(123.45), -1));
    }

    public static double fastInvSqrt(double x) {
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i);
        x *= (1.5d - xhalf * x * x);
        return x;
    }

    public static double invSqrt(double x) {
        return 1 / Math.sqrt(x);
    }
}

