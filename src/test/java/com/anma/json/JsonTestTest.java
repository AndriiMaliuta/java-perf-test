package com.anma.json;

import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JsonTestTest {

    static JsonExe jsonExe = null;

    @BeforeAll
    static void beforeAll() {
        jsonExe = new JsonExe();
    }

    @Test
    void testJson() {
        Gson gson = new GsonBuilder().create();

        String json1 = "[{\"name\":{\"common\":\"Ukraine\",\"official\":\"Ukraine\",\"nativeName\":{\"ukr\":{\"official\":\"Україна\"," +
                "\"common\":\"Україна\"}}},\"tld\":[\".ua\",\".укр\"],\"cca2\":\"UA\",\"ccn3\":\"804\",\"cca3\":\"UKR\",\"cioc\":\"UKR\"," +
                "\"independent\":true,\"status\":\"officially-assigned\",\"unMember\":true,\"currencies\":{\"UAH\":{\"name\":\"Ukrainian hryvnia\",\"symbol\"" +
                ":\"₴\"}},\"idd\":{\"root\":\"+3\",\"suffixes\":[\"80\"]},\"capital\":[\"Kyiv\"],\"altSpellings\":[\"UA\",\"Ukrayina\"],\"region\":\"Europe\"," +
                "\"subregion\":\"Eastern Europe\",\"languages\":{\"ukr\":\"Ukrainian\"},\"translations\":{\"ara\":{\"official\":\"أوكرانيا\",\"common\":\"أوكرانيا\"}," +
                "\"ces\":{\"official\":\"Ukrajina\",\"common\":\"Ukrajina\"},\"cym\":{\"official\":\"Ukraine\",\"common\":\"Ukraine\"},\"deu\":{\"official\":\"Ukraine\"," +
                "\"common\":\"Ukraine\"},\"est\":{\"official\":\"Ukraina\",\"common\":\"Ukraina\"},\"fin\":{\"official\":\"Ukraina\",\"common\":\"Ukraina\"},\"" +
                "fra\":{\"official\":\"Ukraine\",\"common\":\"Ukraine\"},\"hrv\":{\"official\":\"Ukrajina\",\"common\":\"Ukrajina\"},\"hun\":{\"official\":\"Ukrajna\"," +
                "\"common\":\"Ukrajna\"},\"ita\":{\"official\":\"Ucraina\",\"common\":\"Ucraina\"},\"jpn\":{\"official\":\"ウクライナ\",\"common\":\"ウクライナ\"},\"kor\":" +
                "{\"official\":\"우크라이나\",\"common\":\"우크라이나\"},\"nld\":{\"official\":\"Oekraïne\",\"common\":\"Oekraïne\"},\"per\":{\"official\":\"اوکراین\",\"common\":" +
                "\"اوکراین\"},\"pol\":{\"official\":\"Ukraina\",\"common\":" +
                "\"Ukraina\"},\"por\":{\"official\":\"Ucrânia\",\"common\":\"Ucrânia\"},\"rus\":{\"official\":\"Украина\",\"common\":\"Украина\"},\"slk\":{\"official\"" +
                ":\"Ukrajina\"" +
                ",\"common\":\"Ukrajina\"},\"spa\":{\"official\":\"Ucrania\",\"common\":\"Ucrania\"},\"swe\":{\"official\":\"Ukraina\",\"common\":\"Ukraina\"},\"urd\":" +
                "{\"official\":\"یوکرین\",\"common\":\"یوکرین\"},\"zho\":{\"official\":\"乌克兰\",\"common\":\"乌克兰\"}},\"latlng\":[49.0,32.0],\"landlocked\":false,\"borders\":" +
                "[\"BLR\",\"HUN\",\"MDA\",\"POL\",\"ROU\",\"RUS\"" +
                ",\"SVK\"],\"area\":603500.0,\"demonyms\":{\"eng\":{\"f\":\"Ukrainian\",\"m\":\"Ukrainian\"},\"fra\":{\"f\":\"Ukrainienne\",\"m\":\"Ukrainien\"}}" +
                ",\"flag\":\"\\uD83C\\uDDFA\\uD83C\\uDDE6\",\"maps\":{\"googleMaps\":\"https://goo.gl/maps/DvgJMiPJ7aozKFZv7\",\"openStreetMaps\":" +
                "\"https://www.openstreetmap.org/relation/60199\"},\"population\":44134693,\"gini\":{\"2019\":26.6},\"fifa\":\"UKR\",\"car\":{\"signs\":[\"UA\"]" +
                ",\"side\":\"right\"},\"timezones\":[\"UTC+02:00\"],\"continents\":[\"Europe\"],\"flags\":{\"png\":\"https://flagcdn.com/w320/ua.png\",\"svg\"" +
                ":\"https://flagcdn.com/ua.svg\"},\"coatOfArms\":{\"png\":\"https://mainfacts.com/media/images/coats_of_arms/ua.png\",\"svg\":\"https://mainfacts.com/media/images/coats_of_arms/ua.svg\"}," +
                "\"startOfWeek\":\"monday\",\"capitalInfo\":{\"latlng\":[50.43,30.52]},\"postalCode\":{\"format\":\"#####\",\"regex\":\"^(\\\\d{5})$\"}}]";

        var urk = gson.fromJson(json1, Country[].class);
        System.out.println(Arrays.toString(urk));


    }

    @Test
    void testCatJson() {
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

class Country {
    Name name;

    static class Name {

        String common;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}