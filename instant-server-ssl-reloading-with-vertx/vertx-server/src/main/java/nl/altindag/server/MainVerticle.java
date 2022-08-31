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
package nl.altindag.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.TrustOptions;
import io.vertx.ext.web.Router;
import nl.altindag.server.service.FileBasedSslUpdateService;
import nl.altindag.ssl.SSLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

    @Override
    public void start() {
        var router = Router.router(vertx);
        router.route().handler(context -> context.json(
                new JsonObject().put("message", "Hello World!")
        ));

        var sslFactory = SSLFactory.builder()
                .withSwappableIdentityMaterial()
                .withIdentityMaterial("identity.jks", "secret".toCharArray())
                .withSwappableTrustMaterial()
                .withTrustMaterial("truststore.jks", "secret".toCharArray())
                .build();

        var sslUpdateService = new FileBasedSslUpdateService(sslFactory);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(sslUpdateService::updateSslMaterial, 1, 1, TimeUnit.MINUTES);
        LOGGER.info("Checking every minute for changes on the keystore and truststore files");

        var serverOptions = new HttpServerOptions()
                .setSsl(true)
                .setClientAuth(ClientAuth.REQUIRED)
                .setKeyCertOptions(KeyCertOptions.wrap(sslFactory.getKeyManager().orElseThrow()))
                .setTrustOptions(TrustOptions.wrap(sslFactory.getTrustManager().orElseThrow()));

        vertx.createHttpServer(serverOptions)
                .requestHandler(router)
                .listen(8443)
                .onSuccess(server -> System.out.println("HTTP server started on port " + server.actualPort()));
    }

}
