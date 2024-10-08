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
package nl.altindag.ssl.trustme.service;

import nl.altindag.ssl.trustme.exception.PingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class PingService {

    private final HttpClient httpClient;

    public PingService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void ping(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .timeout(Duration.ofMinutes(1))
                .uri(URI.create(url))
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            throw new PingException(e);
        }
    }

}
