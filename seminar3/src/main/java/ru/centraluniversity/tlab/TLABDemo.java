package ru.centraluniversity.tlab;

import java.util.Arrays;

//-Xlog:gc,tlab -Xmx10m
public class TLABDemo {

    public static void main(String[] args) {
        var sum = 0;
        for (int i = 0; i < 10_000; i++) {
            if (i % 100 == 0) {
                var array = new byte[1_500_000];
                sum += Arrays.hashCode(array);
            }
            var obj = new Object(); // Создаём много мелких объектов
            sum += obj.hashCode();
        }
        System.out.println(sum);
        System.out.println("Allocation done. Press Enter to exit.");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
