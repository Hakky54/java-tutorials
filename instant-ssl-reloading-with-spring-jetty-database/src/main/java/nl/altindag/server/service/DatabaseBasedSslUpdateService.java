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
package nl.altindag.server.service;

import nl.altindag.server.model.SSLMaterial;
import nl.altindag.server.repository.SSLMaterialRepository;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.pem.util.PemUtils;
import nl.altindag.ssl.util.SSLFactoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class DatabaseBasedSslUpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseBasedSslUpdateService.class);

    private final SSLFactory baseSslFactory;
    private final SSLMaterialRepository sslMaterialRepository;

    private ZonedDateTime lastModifiedTime = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);

    public DatabaseBasedSslUpdateService(SSLFactory baseSslFactory, SSLMaterialRepository sslMaterialRepository) {
        this.baseSslFactory = baseSslFactory;
        this.sslMaterialRepository = sslMaterialRepository;
    }

    /**
     * This setup is very basic, and therefore currently does not validate if the content on the database has been updated.
     * Fetches every 10 seconds the ssl material from the database to update the server.
     */
    @Scheduled(cron = "*/10 * * * * *")
    private void updateSslMaterial() {
        LOGGER.info("Fetching ssl material...");

        SSLMaterial sslMaterial = sslMaterialRepository.findById(1L)
                .orElseThrow();

        ZonedDateTime sslMaterialUpdatedAt = ZonedDateTime.ofInstant(sslMaterial.getUpdatedAt().toInstant(), ZoneOffset.UTC);

        if(sslMaterialUpdatedAt.isBefore(lastModifiedTime) || sslMaterialUpdatedAt.isEqual(lastModifiedTime)) {
            LOGGER.info("No changes detected. Skipping of refreshing the ssl configuration");
            return;
        }

        LOGGER.info("Changes detected. Starting to update ssl material and refreshing the ssl configuration");

        X509ExtendedKeyManager keyManager = PemUtils.parseIdentityMaterial(sslMaterial.getIdentityContent(), sslMaterial.getIdentityPassword().toCharArray());
        X509ExtendedTrustManager trustManager = PemUtils.parseTrustMaterial(sslMaterial.getTrustedCertificates());

        SSLFactory updatedSslFactory = SSLFactory.builder()
                .withIdentityMaterial(keyManager)
                .withTrustMaterial(trustManager)
                .build();

        SSLFactoryUtils.reload(baseSslFactory, updatedSslFactory);

        lastModifiedTime = sslMaterialUpdatedAt;

        LOGGER.info("Finished updating ssl material and refreshing the ssl configuration");
    }

}
