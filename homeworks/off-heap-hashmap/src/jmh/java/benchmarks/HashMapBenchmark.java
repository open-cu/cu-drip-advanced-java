package benchmarks;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;
import ru.centraluniversity.OffHeapIntIntHashMap;
import ru.centraluniversity.OffHeapIntIntHashMapImpl;

import java.util.HashMap;

public class HashMapBenchmark {

    @Param({"100", "1000", "10000", "1000000"})
    private int size;

    private HashMap<Integer, Integer> jdkMap;
    private OffHeapIntIntHashMap offHeapMap;

    @Setup(Level.Iteration)
    public void setup() {
        jdkMap = new HashMap<>();
        offHeapMap = new OffHeapIntIntHashMapImpl();

        for (int i = 0; i < size; i++) {
            jdkMap.put(i, i);
            offHeapMap.put(i, i);
        }
    }

    @TearDown(Level.Iteration)
    public void tearDown() {
        offHeapMap.free();
    }

}