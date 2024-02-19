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
package nl.altindag.server.config;

import nl.altindag.ssl.SSLFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSLConfig {

    @Bean
    public SSLFactory sslFactory(@Value("${ssl.keystore-path}") String keyStorePath,
                                 @Value("${ssl.keystore-password}") char[] keyStorePassword,
                                 @Value("${ssl.truststore-path}") String trustStorePath,
                                 @Value("${ssl.truststore-password}") char[] trustStorePassword,
                                 @Value("${ssl.client-auth}") boolean isClientAuthenticationRequired) {

        return SSLFactory.builder()
                .withSwappableIdentityMaterial()
                .withSwappableTrustMaterial()
                .withIdentityMaterial(keyStorePath, keyStorePassword)
                .withTrustMaterial(trustStorePath, trustStorePassword)
                .withNeedClientAuthentication(isClientAuthenticationRequired)
                .build();
    }

}
