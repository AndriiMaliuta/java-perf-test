package com.anma;

import com.anma.sql.DBTest;
import com.anma.web.ScrapeTest;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        var start = Instant.now();
        try {
            ScrapeTest.scrape();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(">>> TIME TAKEN = " + Duration.between(start, Instant.now()).toMillis());
    }

}
