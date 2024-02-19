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
import org.apache.tomcat.util.net.SSLContext;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public final class TomcatSSLContext implements SSLContext {

    private final SSLFactory sslFactory;

    public TomcatSSLContext(SSLFactory sslFactory) {
        this.sslFactory = sslFactory;
    }

    @Override
    public void init(KeyManager[] kms, TrustManager[] tms, SecureRandom sr) {
        // not needed to initialize as it is already initialized
    }

    @Override
    public void destroy() {

    }

    @Override
    public SSLSessionContext getServerSessionContext() {
        return sslFactory.getSslContext().getServerSessionContext();
    }

    @Override
    public SSLEngine createSSLEngine() {
        return sslFactory.getSSLEngine();
    }

    @Override
    public SSLServerSocketFactory getServerSocketFactory() {
        return sslFactory.getSslServerSocketFactory();
    }

    @Override
    public SSLParameters getSupportedSSLParameters() {
        return sslFactory.getSslParameters();
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        return sslFactory.getKeyManager()
                .map(keyManager -> keyManager.getCertificateChain(alias))
                .orElseThrow();
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return sslFactory.getTrustedCertificates().toArray(new X509Certificate[0]);
    }

}
