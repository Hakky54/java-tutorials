package nl.altindag.server.service;

import nl.altindag.server.model.SSLMaterial;
import nl.altindag.server.repository.SSLMaterialRepository;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.PemUtils;
import nl.altindag.ssl.util.SSLFactoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;

@Service
public class DatabaseBasedSslUpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseBasedSslUpdateService.class);

    private final SSLFactory baseSslFactory;
    private final SSLMaterialRepository sslMaterialRepository;

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
        LOGGER.info("Starting to update ssl material");

        SSLMaterial sslMaterial = sslMaterialRepository.findById(1L)
                .orElseThrow();

        X509ExtendedKeyManager keyManager = PemUtils.parseIdentityMaterial(sslMaterial.getIdentityContent(), sslMaterial.getIdentityPassword().toCharArray());
        X509ExtendedTrustManager trustManager = PemUtils.parseTrustMaterial(sslMaterial.getTrustedCertificates());

        SSLFactory updatedSslFactory = SSLFactory.builder()
                .withIdentityMaterial(keyManager)
                .withTrustMaterial(trustManager)
                .build();

        SSLFactoryUtils.reload(baseSslFactory, updatedSslFactory);

        LOGGER.info("Finished updating ssl material");
    }

}
