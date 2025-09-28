package ru.centraluniversity.leak;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Gatherers;
import java.util.stream.LongStream;

// -Xmx64m -XX:+HeapDumpOnOutOfMemoryError
public class LeakMain {

    private static Map<User, Long> scoreboard = new HashMap<>();


    public static void main(String[] args) {
        Random random = new Random();
        LongStream.generate(random::nextLong)
            .boxed()
            .gather(Gatherers.windowFixed(2))
            .forEach(list ->
                updateScoreboard(
                    new User(list.get(0) % 10),
                    list.get(1)
                )
            );
    }

    public static void updateScoreboard(User user, long score) {
        scoreboard.put(user, score);
    }
}
