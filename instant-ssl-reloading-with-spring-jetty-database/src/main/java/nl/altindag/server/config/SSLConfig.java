package nl.altindag.server.config;

import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.JettySslUtils;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSLConfig {

    @Bean
    public SSLFactory sslFactory() {
        return SSLFactory.builder()
                .withDummyIdentityMaterial()
                .withDummyTrustMaterial()
                .withSwappableIdentityMaterial()
                .withSwappableTrustMaterial()
                .build();
    }

    @Bean
    public SslContextFactory.Server sslContextFactory(SSLFactory sslFactory) {
        return JettySslUtils.forServer(sslFactory);
    }

}
