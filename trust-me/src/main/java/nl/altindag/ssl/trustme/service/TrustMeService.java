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

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import nl.altindag.ssl.model.TrustManagerParameters;
import nl.altindag.ssl.trustme.util.Logger;
import nl.altindag.ssl.util.TrustManagerUtils;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Service
public class TrustMeService {

    private static final Function<TrustManagerParameters, String> hostnameExtractor = trustManagerParameters -> trustManagerParameters
            .getSslEngine().map(SSLEngine::getPeerHost)
            .or(() -> trustManagerParameters.getSocket().map(socket -> socket.getInetAddress().getHostName()))
            .orElseThrow();

    private final Map<String, Boolean> hostnameToShouldBeTrusted = new ConcurrentHashMap<>();

    private final X509ExtendedTrustManager trustManager;

    public TrustMeService(X509ExtendedTrustManager trustManager) {
        this.trustManager = trustManager;
    }

    public boolean verify(TrustManagerParameters trustManagerParameters) {
        String hostname = hostnameExtractor.apply(trustManagerParameters);

        Boolean isTrusted = hostnameToShouldBeTrusted.get(hostname);
        if (isTrusted != null) {
            return isTrusted;
        }

        X509Certificate certificate = trustManagerParameters.getChain()[0];
        Platform.runLater(() -> askUserToTrustServerCertificate(hostname, certificate));
        return hostnameToShouldBeTrusted.getOrDefault(hostname, false);
    }

    private void askUserToTrustServerCertificate(String hostname, X509Certificate certificate) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to trust the certificate of " + hostname, ButtonType.YES, ButtonType.NO);
        alert.setTitle("Trust confirmation");

        Optional<ButtonType> buttonType = alert.showAndWait();
        boolean shouldBeTrusted = buttonType.filter(type -> type == ButtonType.YES).isPresent();
        hostnameToShouldBeTrusted.put(hostname, shouldBeTrusted);

        if (shouldBeTrusted) {
            Logger.log("User trusted server. Adding the certificate to the list of trusted certificates....");
            TrustManagerUtils.addCertificate(trustManager, certificate);
            Logger.log("Server is now trusted");
        } else {
            Logger.log("User decided not to trust the server. Not prompting anymore");
        }
    }

}
