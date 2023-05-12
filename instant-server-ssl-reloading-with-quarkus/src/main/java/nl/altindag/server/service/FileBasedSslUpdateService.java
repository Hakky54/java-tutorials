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

import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.SSLFactoryUtils;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class FileBasedSslUpdateService {

    private static final Logger LOGGER = Logger.getLogger(FileBasedSslUpdateService.class);

    private static final Path identityPath = Path.of("/path/to/your/identity.jks");
    private static final Path trustStorePath = Path.of("/path/to/your/truststore.jks");
    private static final char[] identityPassword = "secret".toCharArray();
    private static final char[] trustStorePassword = "secret".toCharArray();

    private ZonedDateTime lastModifiedTimeIdentityStore = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
    private ZonedDateTime lastModifiedTimeTrustStore = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);

    private final SSLFactory baseSslFactory;

    public FileBasedSslUpdateService(SSLFactory baseSslFactory) {
        this.baseSslFactory = baseSslFactory;
        LOGGER.info("Started listening for any changes on the keystore and truststore files...");
    }

    public void updateSslMaterial() {
        try {
            if (Files.exists(identityPath) && Files.exists(trustStorePath)) {
                BasicFileAttributes identityAttributes = Files.readAttributes(identityPath, BasicFileAttributes.class);
                BasicFileAttributes trustStoreAttributes = Files.readAttributes(trustStorePath, BasicFileAttributes.class);

                boolean identityUpdated = lastModifiedTimeIdentityStore.isBefore(ZonedDateTime.ofInstant(identityAttributes.lastModifiedTime().toInstant(), ZoneOffset.UTC));
                boolean trustStoreUpdated = lastModifiedTimeTrustStore.isBefore(ZonedDateTime.ofInstant(trustStoreAttributes.lastModifiedTime().toInstant(), ZoneOffset.UTC));

                if (identityUpdated && trustStoreUpdated) {
                    LOGGER.info("Keystore files have been changed. Trying to read the file content and preparing to update the ssl material");

                    SSLFactory updatedSslFactory = SSLFactory.builder()
                            .withIdentityMaterial(identityPath, identityPassword)
                            .withTrustMaterial(trustStorePath, trustStorePassword)
                            .build();

                    SSLFactoryUtils.reload(baseSslFactory, updatedSslFactory);

                    lastModifiedTimeIdentityStore = ZonedDateTime.ofInstant(identityAttributes.lastModifiedTime().toInstant(), ZoneOffset.UTC);
                    lastModifiedTimeTrustStore = ZonedDateTime.ofInstant(trustStoreAttributes.lastModifiedTime().toInstant(), ZoneOffset.UTC);

                    LOGGER.info("Updating ssl material finished");
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
