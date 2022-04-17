package com.anma;

import com.anma.json.JsonTest;

import java.io.IOException;
import java.net.Authenticator;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var start = Instant.now();
        JsonTest.testJson();
        System.out.println(Duration.between(start, Instant.now()).toMillis());
    }

    public static void testFiles() {
        for (int i = 1; i < 200; i++) {
            try {
                List<String> strings = Files.readAllLines(Paths.get("/home/andrii/docs/file" + i + ".txt"));
                System.out.println(strings);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void testHttp() {
        var url = "https://marketplace.atlassian.com/rest/2/vendors/1216206/reporting/sales/transactions?limit=30";
//        var tokenStr = String.format("quadr988@gmail.com:%s", System.getenv("ATLAS_TOKEN"));
        var tokenStr = "quadr988@gmail.com:blPz2E6KDXpfFr6pU4hM59C9";
        var token = Base64.getEncoder().encode(tokenStr.getBytes());
        String header1 = String.format("Basic %s", new String(token));
        System.out.println(header1);

        var request = HttpRequest.newBuilder(URI.create(url))
                .GET()
                .header("Authorization", "Basic " + new String(token))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    private static void asyncRequest(HttpClient client, HttpRequest request) {
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
