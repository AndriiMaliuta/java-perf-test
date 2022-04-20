package com.anma.files;

import com.anma.count.WordCounter;
import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestFilesTest {

    Logger logger = LoggerFactory.getLogger(TestFilesTest.class);
    StringBuilder builder = new StringBuilder();
    Stopwatch stopwatch = Stopwatch.createStarted();

    @Test
    void testFiles() {
        for (int i = 1; i < 200; i++) {
            try {
                List<String> strings = Files.readAllLines(Paths.get("/home/andrii/docs/file" + i + ".txt"));
                strings.forEach(builder::append);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        var words = WordCounter.countWords(builder.toString());
        System.out.println(words);
        stopwatch.stop();
//        logger.info("Time is " + stopwatch);
        System.out.println(">>> TIME TAKEN = " + stopwatch);
    }
}