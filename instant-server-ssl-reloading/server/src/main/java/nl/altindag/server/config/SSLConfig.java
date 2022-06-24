package nl.altindag.server.config;

import nl.altindag.server.model.ApplicationProperty;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.JettySslUtils;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSLConfig {

    @Bean
    public SSLFactory sslFactory(ApplicationProperty applicationProperty) {
        return SSLFactory.builder()
                .withSwappableIdentityMaterial()
                .withIdentityMaterial(applicationProperty.getKeystorePath(), applicationProperty.getKeystorePassword())
                .withSwappableTrustMaterial()
                .withTrustMaterial(applicationProperty.getTruststorePath(), applicationProperty.getTruststorePassword())
                .withNeedClientAuthentication(applicationProperty.isSslClientAuth())
                .build();
    }

    @Bean
    public SslContextFactory.Server sslContextFactory(SSLFactory sslFactory) {
        return JettySslUtils.forServer(sslFactory);
    }

}
