package ru.centraluniversity;

import static ru.centraluniversity.Main.fastInvSqrt;
import static ru.centraluniversity.Main.invSqrt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1)
@Measurement(iterations = 3)
public class SqrtBenchmark {

    public double[] numbers;


    @Setup
    public void init() throws IOException {
        var inputStream = getClass().getClassLoader().getResourceAsStream("numbers.txt");
        var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        numbers = reader.lines().mapToDouble(Double::parseDouble).toArray();
    }

    @Benchmark
    public double invSqrtBench() {
        var sum = 0.0;
        for (double number : numbers) {
            sum += 1 / Math.sqrt(number);
        }
        return sum;
    }

    @Benchmark
    public void invSqrtBenchWithoutSum() {
        for (double number : numbers) {
            invSqrt(number);
        }
    }

    @Benchmark
    public void invSqrtBenchWithBh(Blackhole bh) {
        for (double number : numbers) {
            bh.consume(invSqrt(number));
        }
    }

    @Benchmark
    public double invSqrtPowBench() {
        var sum = 0.0;
        for (double number : numbers) {
            sum += Math.pow(Math.sqrt(number), -1);
        }
        return sum;
    }

    @Benchmark
    public double fastInvSqrtBench() {
        var sum = 0.0;
        for (double number : numbers) {
            sum += fastInvSqrt(number);
        }
        return sum;
    }
}
