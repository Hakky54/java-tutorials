package nl.altindag.client;

import nl.altindag.ssl.SSLFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        SSLFactory sslFactory = SSLFactory.builder()
                .withIdentityMaterial("identity.jks", "secret".toCharArray())
                .withTrustMaterial("truststore.jks", "secret".toCharArray())
                .withProtocols("TLSv1.3")
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .sslParameters(sslFactory.getSslParameters())
                .sslContext(sslFactory.getSslContext())
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://localhost:443/hello"))
                .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        System.out.printf("Received [%d] status code from the server%n", response.statusCode());
    }

}
