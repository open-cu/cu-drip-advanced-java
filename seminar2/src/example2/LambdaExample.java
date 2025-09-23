package ru.sin.example2;

import java.util.function.Function;

public class LambdaExample {
    public static void main(String[] args) {
        Function<Integer, Integer> f = x -> x + 1;
        System.out.println(f.apply(5));
    }
}