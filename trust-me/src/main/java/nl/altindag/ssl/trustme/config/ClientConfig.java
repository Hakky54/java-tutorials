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
package nl.altindag.ssl.trustme.config;

import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.trustme.service.TrustMeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.net.ssl.X509ExtendedTrustManager;
import java.net.http.HttpClient;
import java.nio.file.Path;

@Configuration
public class ClientConfig {

    private static final Path TRUSTSTORE_PATH = Path.of(System.getProperty("user.dir"), "truststore.jks");
    private static final char[] TRUSTSTORE_PASSWORD = "changeit".toCharArray();
    private static final String TRUSTSTORE_TYPE = "PKCS12";

    @Bean
    public HttpClient httpClient(SSLFactory sslFactory) {
        return HttpClient.newBuilder()
                .sslContext(sslFactory.getSslContext())
                .sslParameters(sslFactory.getSslParameters())
                .build();
    }

    @Bean
    public SSLFactory sslFactory(@Lazy TrustMeService trustMeService) {
        return SSLFactory.builder()
                .withInflatableTrustMaterial(TRUSTSTORE_PATH, TRUSTSTORE_PASSWORD, TRUSTSTORE_TYPE, trustMeService::verify)
                .build();
    }

    @Bean
    public X509ExtendedTrustManager trustManager(SSLFactory sslFactory) {
        return sslFactory.getTrustManager().orElseThrow();
    }

}
