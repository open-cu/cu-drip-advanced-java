package ru.centraluniversity.jit.exercise;

import java.util.Random;

interface DiscountPolicy {
    double apply(double price);
}

class NoDiscount implements DiscountPolicy {
    public double apply(double price) {
        return price;
    }
}

class TenPercentDiscount implements DiscountPolicy {
    public double apply(double price) {
        return price * 0.9;
    }
}

class Order {
    final int id;
    final double price;

    Order(int id, double price) {
        this.id = id;
        this.price = price;
    }

    double computeTotal() {
        DiscountPolicy policy = (id % 2 == 0) ? new NoDiscount() : new TenPercentDiscount();
        return policy.apply(price);
    }
}

public class OrderProcessingBad {

    static Order lastOrder;

    public static double processOrders(int n) {
        Random rnd = new Random(42);
        Order[] orders = new Order[n];
        for (int i = 0; i < n; i++) {
            orders[i] = new Order(i, rnd.nextDouble() * 1000);
            lastOrder = orders[i];
        }

        double total = 0;
        for (Order o : orders) {
            total += o.computeTotal();
        }

        double dummy = 0;
        for (int i = 0; i < n; i++) {
            dummy += Math.sin(rnd.nextDouble());
        }

        int dead = n * 42; // DCE возможно
        return total + dummy + dead;
    }
}