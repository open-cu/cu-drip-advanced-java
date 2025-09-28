package ru.centraluniversity;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import ru.centraluniversity.escape.Point;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
public class EscapeAnalysisBenchmark {

    @Benchmark
    @Fork(value = 1, jvmArgsPrepend = {"-XX:+DoEscapeAnalysis"}) // можно менять на -XX:-DoEscapeAnalysis
    public long testComputeDistance(Blackhole blackhole) {
        Point p = new Point(10, 20);
        return (int) Math.abs(p.getX() - p.getY());
    }

    @Benchmark
    @Fork(value = 1, jvmArgsPrepend = {"-XX:-DoEscapeAnalysis"}) // можно менять на -XX:-DoEscapeAnalysis
    public long testComputeDistanceWoEscapeAnalysis(Blackhole blackhole) {
        Point p = new Point(10, 20);
        return (int) Math.abs(p.getX() - p.getY());
    }
}
