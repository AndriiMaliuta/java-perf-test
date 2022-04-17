package com.anma.web;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ScrapeTest {
    public static void scrape() throws IOException {
        var html = Jsoup.parse(new URL("https://www.psycopg.org/docs/install.html"), 10000);
        var paras = html.select("p");
        paras.forEach(System.out::println);
    }
}
