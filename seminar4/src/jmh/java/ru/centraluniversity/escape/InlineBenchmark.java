package ru.centraluniversity.escape;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import ru.centraluniversity.jit.inline.InlineExample;

import java.util.concurrent.TimeUnit;

/**
 * Сильного выйгрыша не будет
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 4, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
public class InlineBenchmark {

    @Benchmark
    public void baseline(Blackhole bh) {
        for (int i = 0; i < 1000; i++) {
            bh.consume(InlineExample.computeBad());
        }
    }

    @Benchmark
    public void optimized(Blackhole bh) {
        for (int i = 0; i < 1000; i++) {
            bh.consume(InlineExample.computeGood());
        }
    }

}
