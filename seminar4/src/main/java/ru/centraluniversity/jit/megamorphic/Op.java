package ru.centraluniversity.jit.megamorphic;

public interface Op { int apply(int x); }

class Add implements Op { public int apply(int x){ return x+1; } }
class Mul implements Op { public int apply(int x){ return x*2; } }
class Sub implements Op { public int apply(int x){ return x-1; } }

