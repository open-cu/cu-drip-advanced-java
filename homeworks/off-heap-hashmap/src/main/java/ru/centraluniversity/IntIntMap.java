package ru.centraluniversity;

public interface IntIntMap {

    void put(int key, int value);

    int get(int key);

    boolean containsKey(int key);

    void remove(int key);

    int size();
}