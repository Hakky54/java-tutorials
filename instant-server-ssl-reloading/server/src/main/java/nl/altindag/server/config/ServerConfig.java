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
package nl.altindag.server.config;

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
    public ConfigurableServletWebServerFactory webServerFactory(SslContextFactory.Server sslContextFactory, @Value("${server.port}") int serverPort) {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();

        JettyServerCustomizer jettyServerCustomizer = server -> {
            ServerConnector serverConnector = new ServerConnector(server, sslContextFactory);
            serverConnector.setPort(serverPort);
            server.setConnectors(new Connector[]{serverConnector});
        };
        factory.setServerCustomizers(Collections.singletonList(jettyServerCustomizer));

        return factory;
    }

}
