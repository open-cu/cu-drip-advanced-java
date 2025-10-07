package ru.centraluniversity.escape;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import ru.centraluniversity.jit.escape.EscapeExample;
import ru.centraluniversity.jit.escape.EscapeExampleOptimized;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
public class EscapeAnalysisBenchmark {

//    @Benchmark
    @Fork(value = 1, jvmArgsPrepend = {"-XX:+PrintCompilation"})
    public void baseline(Blackhole bh) {
        for (int i = 0; i < 1000000; i++) {
            bh.consume(EscapeExample.compute());
        }
    }

//    @Benchmark
    @Fork(value = 1, jvmArgsPrepend = {"-XX:+PrintCompilation"})
    public void optimized(Blackhole bh) {
        for (int i = 0; i < 1000000; i++) {
            bh.consume(EscapeExampleOptimized.compute());
        }
    }
}
