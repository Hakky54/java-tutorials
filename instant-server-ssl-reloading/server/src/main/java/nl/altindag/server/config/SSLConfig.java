package nl.altindag.server.config;

import nl.altindag.server.model.ApplicationProperty;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.JettySslUtils;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;

@Configuration
public class SSLConfig {

    @Bean
    public X509ExtendedKeyManager keyManager(SSLFactory sslFactory) {
        return sslFactory.getKeyManager().orElseThrow();
    }

    @Bean
    public X509ExtendedTrustManager trustManager(SSLFactory sslFactory) {
        return sslFactory.getTrustManager().orElseThrow();
    }

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
