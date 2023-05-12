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

import io.quarkus.vertx.http.HttpServerOptionsCustomizer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.TrustOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.altindag.server.service.FileBasedSslUpdateService;
import nl.altindag.ssl.SSLFactory;
import org.jboss.logging.Logger;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class ServerConfig implements HttpServerOptionsCustomizer {

    private final Logger LOGGER;

    @Inject
    public ServerConfig(Logger logger) {
        LOGGER = logger;
    }

    @Override
    public void customizeHttpsServer(HttpServerOptions options) {
        try {
            // Example usage of initially loading the keystores from the classpath
            // as absolute path within quarkus. Getting the files with class.getResource()
            // is not needed if you are loading your files from the file system.
            var identityUrl = ServerConfig.class.getResource("/identity.jks");
            var trustUrl = ServerConfig.class.getResource("/truststore.jks");

            var sslFactory = SSLFactory.builder()
                    .withSwappableIdentityMaterial()
                    .withIdentityMaterial(Paths.get(identityUrl.toURI()), "secret".toCharArray())
                    .withSwappableTrustMaterial()
                    .withTrustMaterial(Paths.get(trustUrl.toURI()), "secret".toCharArray())
                    .build();

            var sslUpdateService = new FileBasedSslUpdateService(sslFactory);

            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(sslUpdateService::updateSslMaterial, 1, 1, TimeUnit.MINUTES);
            LOGGER.info("Checking every minute for changes on the keystore and truststore files");

            options.setSsl(true)
                    .setKeyCertOptions(KeyCertOptions.wrap(sslFactory.getKeyManager().orElseThrow()))
                    .setTrustOptions(TrustOptions.wrap(sslFactory.getTrustManager().orElseThrow()));

            HttpServerOptionsCustomizer.super.customizeHttpsServer(options);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
