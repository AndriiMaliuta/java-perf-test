package com.anma;

import com.google.common.base.Stopwatch;
import com.google.common.cache.AbstractCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Timer;

public class MiscTest {

    @Test
    void testAll() {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Assertions.assertEquals(1, 1);

        stopwatch.stop();
        Duration duration = stopwatch.elapsed();
        System.out.println(duration);
        System.out.println("time: " + stopwatch); // formatted string like "12.3 ms"
    }

    @Test
    void clockTest() {
        Timer timer = new Timer();
//        AbstractCache.SimpleStatsCounter counter = AbstractCache.SimpleStatsCounter
//        timer.schedule();
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    }
}
