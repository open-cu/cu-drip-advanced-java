package benchmarks;

import org.openjdk.jmh.annotations.Param;

public class HashMapBenchmark {

    @Param({"100", "1000", "10000", "100000", "1000000"})
    private int size;

}