package com.anma.json;

import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.time.Duration;

class Cat {
    String name;
    int age;
    String color;

    public Cat(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }
}

public class JsonTest {

    public static void testJson() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Gson gson = new GsonBuilder().create();
        for (int i = 0; i < 1000; i++) {
            String json = gson.toJson(new Cat("Murz", 7, "white"));
            System.out.println(json);
        }
        stopwatch.stop();
//        Duration duration = stopwatch.elapsed();
        System.out.println(">>> TIME TAKEN = " + stopwatch); // formatted string like "12.3 ms"
    }
}
