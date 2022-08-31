/*
 * Copyright 2022 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.altindag.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.altindag.admin.model.SSLUpdateRequest;
import nl.altindag.ssl.SSLFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException {
        char[] keyStorePassword = "secret".toCharArray();

        SSLFactory sslFactory = SSLFactory.builder()
                .withIdentityMaterial("keystores/admin/identity.jks", keyStorePassword)
                .withTrustMaterial("keystores/admin/truststore.jks", keyStorePassword)
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .sslParameters(sslFactory.getSslParameters())
                .sslContext(sslFactory.getSslContext())
                .build();

        byte[] identity = App.class.getClassLoader().getResourceAsStream("keystores/server/identity.jks").readAllBytes();
        byte[] truststore = App.class.getClassLoader().getResourceAsStream("keystores/server/truststore.jks").readAllBytes();
        SSLUpdateRequest sslUpdateRequest = new SSLUpdateRequest(identity, keyStorePassword, truststore, keyStorePassword);

        String body = objectMapper.writeValueAsString(sslUpdateRequest);

        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .uri(URI.create("https://localhost:8443/admin/ssl"))
                .build();

        HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.statusCode());
    }

}
