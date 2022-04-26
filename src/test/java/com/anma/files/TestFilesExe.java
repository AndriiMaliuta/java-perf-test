package com.anma.files;

import com.anma.count.WordCounter;
import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestFilesExe {

    Logger logger = LoggerFactory.getLogger(TestFilesExe.class);
    StringBuilder builder = new StringBuilder();
    Stopwatch stopwatch = Stopwatch.createStarted();

    @Test
    void testFiles() {
        int count = 500;
        for (int i = 1; i < count; i++) {
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

    @Test
    void testGetFileWords() {
        try {
            int count = 0;
            var lines = Files.readAllLines(Path.of("/home/andrii/docs/file100.txt"));
            List<String> allWords = new ArrayList<>();
//            System.out.println(lines);
            for(String line : lines) {
                var words = line.split(" ");
                allWords.addAll(Arrays.asList(words));
            }
            for (String word: allWords) {
                count += 1;
            }
            System.out.println(count);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}