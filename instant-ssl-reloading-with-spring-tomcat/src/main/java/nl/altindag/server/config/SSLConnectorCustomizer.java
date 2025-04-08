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

import nl.altindag.ssl.SSLFactory;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.tomcat.util.net.SSLContext;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.net.SSLHostConfigCertificate;
import org.apache.tomcat.util.net.SSLHostConfigCertificate.Type;
import org.apache.tomcat.util.net.SSLUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSLConnectorCustomizer implements TomcatConnectorCustomizer {

    private final SSLFactory sslFactory;
    private final int port;

    public SSLConnectorCustomizer(SSLFactory sslFactory, @Value("${server.port}") int port) {
        this.sslFactory = sslFactory;
        this.port = port;
    }

    @Override
    public void customize(Connector connector) {
        connector.setScheme("https");
        connector.setSecure(true);
        connector.setPort(port);

        AbstractHttp11Protocol<?> protocol = (AbstractHttp11Protocol<?>) connector.getProtocolHandler();
        protocol.setSSLEnabled(true);

        SSLHostConfig sslHostConfig = new SSLHostConfig();
        SSLHostConfigCertificate certificate = new SSLHostConfigCertificate(sslHostConfig, Type.UNDEFINED);
        SSLContext sslContext = SSLUtil.createSSLContext(
                sslFactory.getSslContext(),
                sslFactory.getKeyManager().orElseThrow(),
                sslFactory.getTrustManager().orElseThrow()
        );

        certificate.setSslContext(sslContext);
        sslHostConfig.addCertificate(certificate);
        protocol.addSslHostConfig(sslHostConfig);
    }

}
