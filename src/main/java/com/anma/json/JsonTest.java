package com.anma.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        Gson gson = new GsonBuilder().create();
        for (int i = 0; i < 1000; i++) {
            String json = gson.toJson(new Cat("Murz", 7, "white"));
            System.out.println(json);
        }
    }
}
