//package com.anma;
//
//import com.google.common.base.Stopwatch;
//import jdk.jfr.Description;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.TimeUnit;
//
//import static java.util.concurrent.TimeUnit.MILLISECONDS;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//import static org.junit.jupiter.api.Assumptions.assumeTrue;
//
//public class StopWatchTest {
//    private static final Logger logger = LoggerFactory.getLogger(StopWatchTest.class);
//
//    private static void logInfo(Description description, String status, long nanos) {
//        String testName = description.value(); // method name
//        logger.info(String.format("Test %s %s, spent %d microseconds",
//                testName, status, TimeUnit.NANOSECONDS.toMicros(nanos)));
//    }
//
////    @Rule
//    public Stopwatch stopwatch = new Stopwatch() {
//        @Override
//        protected void succeeded(long nanos, Description description) {
//            logInfo(description, "succeeded", nanos);
//        }
//
//        @Override
//        protected void failed(long nanos, Throwable e, Description description) {
//            logInfo(description, "failed", nanos);
//        }
//
//        @Override
//        protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
//            logInfo(description, "skipped", nanos);
//        }
//
//        @Override
//        protected void finished(long nanos, Description description) {
//            logInfo(description, "finished", nanos);
//        }
//    };
//
//    @Test
//    public void succeeds() {
//    }
//
//    @Test
//    public void fails() {
//        fail();
//    }
//
//    @Test
//    public void skips() {
//        assumeTrue(false);
//    }
//
//    @Test
//    public void performanceTest() throws InterruptedException {
//        // An example to assert runtime:
//        long delta = 30;
//        Thread.sleep(300L);
//        assertEquals(300d, stopwatch.runtime(MILLISECONDS), delta);
//        Thread.sleep(500L);
//        assertEquals(800d, stopwatch.runtime(MILLISECONDS), delta);
//    }
//}
