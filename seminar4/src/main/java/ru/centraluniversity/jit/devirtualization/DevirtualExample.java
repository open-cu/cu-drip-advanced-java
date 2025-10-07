package ru.centraluniversity.jit.devirtualization;

// Базовый класс
class Base {
    int value;
    Base(int v){ this.value = v; }
    int compute() { return value + 1; }
}

// Дочерний класс
final class Child extends Base {
    Child(int v){ super(v); }
    @Override
    int compute() { return value * 2; }
}

public class DevirtualExample {

    public static int virtBad() {
        Base[] arr = new Base[1_000_000];
        for(int i=0;i<arr.length;i++) arr[i] = new Child(i);
        int sum = 0;
        for(Base b : arr) sum += b.compute(); // каждый вызов virtual
        return sum;
    }

    public static int virtGood() {
        Child[] arr = new Child[1_000_000];
        for(int i=0;i<arr.length;i++) arr[i] = new Child(i);
        int sum = 0;
        for(Child c : arr) sum += c.compute(); // direct call
        return sum;
    }
}