package ru.centraluniversity;

public interface OffHeapIntIntHashMap {

    void put(int key, int value);

    int get(int key);

    boolean containsKey(int key);

    boolean remove(int key);

    int size();

    void clear();

    void free();
}