package com.anma.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

public class HttpExe {

    public static void testHttp() {
        var url = "https://marketplace.atlassian.com/rest/2/vendors/1216206/reporting/sales/transactions?limit=30";
        var tokenStr = String.format("quadr988@gmail.com:%s", System.getenv("ATLAS_TOKEN"));
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
