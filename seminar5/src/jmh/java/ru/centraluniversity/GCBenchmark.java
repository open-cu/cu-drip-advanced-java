package ru.centraluniversity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 1, time = 10)
@Measurement(iterations = 1, time = 60)
@Threads(4)
public class GCBenchmark {

    // === НАСТРАИВАЕМЫЕ ПАРАМЕТРЫ ===
//    @Param({"0.9", "0.5", "0.1"})
    @Param({"0.9"})
    public double shortLivedRatio; // Доля короткоживущих объектов (остальное — долгоживущие)

    @Param({ "1024"})
//    @Param({"128", "1024", "4096"})
    public int shortLivedSize; // Размер короткоживущего объекта в байтах

//    @Param({"10240", "32768", "65536"})
    @Param({"32768"})
    public int longLivedSize; // Размер долгоживущего объекта в байтах

    @Param({"10000"})
    public int maxLongLivedCount; // Макс. число долгоживущих объектов (чтобы не исчерпать память)

    private final List<byte[]> longLivedObjects = new ArrayList<>();

    @Benchmark
    @Fork(value = 1, jvmArgsPrepend = {"-XX:+UseG1GC", "-Xmx8g"})
    public void g1(Blackhole blackhole) {
        doWork(blackhole);
    }

    @Benchmark
    @Fork(value = 1, jvmArgsPrepend = {"-XX:+UseZGC", "-Xmx8g"})
    public void zgc(Blackhole blackhole) {
        doWork(blackhole);

    }

    @Benchmark
    @Fork(value = 1, jvmArgsPrepend = {"-XX:+UseShenandoahGC", "-Xmx8g"})
    public void shenandoah(Blackhole blackhole) {
        doWork(blackhole);

    }

    @Benchmark
    @Fork(value = 1, jvmArgsPrepend = {"-XX:+UseParallelGC", "-Xmx8g"})
    public void parallel(Blackhole blackhole) {
        doWork(blackhole);

    }

    @Benchmark
    @Fork(value = 1, jvmArgsPrepend = {"-XX:+UseSerialGC", "-Xmx8g"})
    public void serial(Blackhole blackhole) {
        doWork(blackhole);
    }

    private void doWork(Blackhole blackhole) {
        double r = ThreadLocalRandom.current().nextDouble();

        if (r < shortLivedRatio) {
            // Короткоживущий объект: создали и "забыли" (для GC)
            byte[] obj = new byte[shortLivedSize];
            blackhole.consume(obj); // Чтобы не оптимизировали
        } else {
            // Долгоживущий объект: сохраняем в список
            byte[] obj = new byte[longLivedSize];
            synchronized (longLivedObjects) {
                longLivedObjects.add(obj);
                if (longLivedObjects.size() > maxLongLivedCount) {
                    longLivedObjects.removeFirst();
                }
            }
        }
    }

    // Опционально: метод для очистки (если нужно тестировать поведение при переполнении Old Gen)
    @TearDown
    public void tearDown() {
        longLivedObjects.clear();
    }
}
