package ru.centraluniversity.escape;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import ru.centraluniversity.jit.devirtualization.DevirtualExample;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
public class DevirtualizationBenchmark {

//    @Benchmark
    public void baseline(Blackhole bh) {
        for (int i = 0; i < 1000; i++) {
            DevirtualExample.virtBad();
        }
    }

//    @Benchmark
    public void optimized(Blackhole bh) {
        for (int i = 0; i < 1000; i++) {
            DevirtualExample.virtGood();
        }
    }

}
