package nl.altindag.server.service;

import nl.altindag.ssl.SSLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class FileBasedSslUpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedSslUpdateService.class);

    private static final Path identityPath = Path.of("/path/to/your/identity.jks");
    private static final Path trustStorePath = Path.of("/path/to/your/truststore.jks");
    private static final char[] identityPassword = "secret".toCharArray();
    private static final char[] trustStorePassword = "secret".toCharArray();

    private ZonedDateTime lastModifiedTimeIdentityStore = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
    private ZonedDateTime lastModifiedTimeTrustStore = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);

    private final SwappableSslService sslService;

    public FileBasedSslUpdateService(SwappableSslService sslService) {
        this.sslService = sslService;
    }

    /**
     * Checks every 10 seconds if the keystore files have been updated.
     * If the files have been updated the service will read the content and update the ssl material
     * within the existing ssl configuration.
     */
    @Scheduled(cron = "*/10 * * * * *")
    private void updateSslMaterial() throws IOException {
        if (Files.exists(identityPath) && Files.exists(trustStorePath)) {
            BasicFileAttributes identityAttributes = Files.readAttributes(identityPath, BasicFileAttributes.class);
            BasicFileAttributes trustStoreAttributes = Files.readAttributes(trustStorePath, BasicFileAttributes.class);

            boolean identityUpdated = lastModifiedTimeIdentityStore.isBefore(ZonedDateTime.ofInstant(identityAttributes.lastModifiedTime().toInstant(), ZoneOffset.UTC));
            boolean trustStoreUpdated = lastModifiedTimeTrustStore.isBefore(ZonedDateTime.ofInstant(trustStoreAttributes.lastModifiedTime().toInstant(), ZoneOffset.UTC));

            if (identityUpdated && trustStoreUpdated) {
                LOGGER.info("Keystore files have been changed. Trying to read the file content and preparing to update the ssl material");

                SSLFactory sslFactory = SSLFactory.builder()
                        .withIdentityMaterial(identityPath, identityPassword)
                        .withTrustMaterial(trustStorePath, trustStorePassword)
                        .build();

                sslService.updateSslMaterials(sslFactory.getKeyManager().orElseThrow(), sslFactory.getTrustManager().orElseThrow());

                lastModifiedTimeIdentityStore = ZonedDateTime.ofInstant(identityAttributes.lastModifiedTime().toInstant(), ZoneOffset.UTC);
                lastModifiedTimeTrustStore = ZonedDateTime.ofInstant(trustStoreAttributes.lastModifiedTime().toInstant(), ZoneOffset.UTC);

                LOGGER.info("Updating ssl material finished");
            }
        }
    }

}
