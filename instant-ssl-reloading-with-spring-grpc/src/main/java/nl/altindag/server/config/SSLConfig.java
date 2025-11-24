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
import org.springframework.boot.ssl.NoSuchSslBundleException;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundleKey;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.ssl.SslManagerBundle;
import org.springframework.boot.ssl.SslOptions;
import org.springframework.boot.ssl.SslStoreBundle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

    @Bean
    public SslBundles sslBundles(SSLFactory sslFactory) {
        return new SslBundles() {
            @Override
            public SslBundle getBundle(String name) throws NoSuchSslBundleException {
                return new SslBundle() {
                    @Override
                    public SslStoreBundle getStores() {
                        return null;
                    }

                    @Override
                    public SslBundleKey getKey() {
                        return null;
                    }

                    @Override
                    public SslOptions getOptions() {
                        return null;
                    }

                    @Override
                    public String getProtocol() {
                        return "";
                    }

                    @Override
                    public SslManagerBundle getManagers() {
                        return new SslManagerBundle() {
                            @Override
                            public KeyManagerFactory getKeyManagerFactory() {
                                return sslFactory.getKeyManagerFactory().get();
                            }

                            @Override
                            public TrustManagerFactory getTrustManagerFactory() {
                                return sslFactory.getTrustManagerFactory().get();
                            }
                        };
                    }
                };
            }

            @Override
            public void addBundleUpdateHandler(String name, Consumer<SslBundle> updateHandler) throws NoSuchSslBundleException {

            }

            @Override
            public void addBundleRegisterHandler(BiConsumer<String, SslBundle> registerHandler) {

            }

            @Override
            public List<String> getBundleNames() {
                return Collections.emptyList();
            }
        };
    }

}
