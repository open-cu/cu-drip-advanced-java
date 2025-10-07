package ru.centraluniversity.jit.const_prop;


public class ConstPropExample {

    public static int runBad(int factor) {
        int sum=0;
        for(int i=0;i<1000;i++) sum+=i*factor;
        return sum;
    }

    public static int runGood() {
        int sum=0;
        for(int i=0;i<1000;i++) sum+=i*5;
        return sum;
    }
}