package nl.altindag.server.config;

import nl.altindag.server.model.ApplicationProperty;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class ServerConfig {

    @Bean
    public ApplicationProperty applicationProperty(
            @Value("${server.port}") int serverPort,
            @Value("${ssl.client-auth}") boolean isClientAuthenticationRequired,
            @Value("${ssl.keystore-path}") String keyStorePath,
            @Value("${ssl.keystore-password}") char[] keyStorePassword,
            @Value("${ssl.truststore-path}") String trustStorePath,
            @Value("${ssl.truststore-password}") char[] trustStorePassword) {

        return new ApplicationProperty()
                .withServerPort(serverPort)
                .withSslClientAuth(isClientAuthenticationRequired)
                .withKeystorePath(keyStorePath)
                .withKeystorePassword(keyStorePassword)
                .withTruststorePath(trustStorePath)
                .withTruststorePassword(trustStorePassword);
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(SslContextFactory.Server sslContextFactory, ApplicationProperty applicationProperty) {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();

        JettyServerCustomizer jettyServerCustomizer = server -> {
            ServerConnector serverConnector = new ServerConnector(server, sslContextFactory);
            serverConnector.setPort(applicationProperty.getServerPort());
            server.setConnectors(new Connector[]{serverConnector});
        };
        factory.setServerCustomizers(Collections.singletonList(jettyServerCustomizer));

        return factory;
    }

}
