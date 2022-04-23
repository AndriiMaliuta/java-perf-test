package com.anma.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class HttpTestExe {

    HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    @BeforeEach
    void setUp() {
    }

    @Test
    void testHttp() {
        var request = HttpRequest.newBuilder()
                .header("Country", "Ukraine")
                .GET()
                .uri(URI.create("https://en.wikipedia.org/wiki/Example.com"))
                .build();
        try {
            var resp = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(resp.body());



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}