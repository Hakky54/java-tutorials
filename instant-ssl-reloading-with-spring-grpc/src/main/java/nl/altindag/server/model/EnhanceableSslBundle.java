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
package nl.altindag.server.model;

import nl.altindag.ssl.SSLFactory;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundleKey;
import org.springframework.boot.ssl.SslManagerBundle;
import org.springframework.boot.ssl.SslOptions;
import org.springframework.boot.ssl.SslStoreBundle;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class EnhanceableSslBundle implements SslBundle {

    private final SSLFactory sslFactory;

    public EnhanceableSslBundle(SSLFactory sslFactory) {
        this.sslFactory = sslFactory;
    }

    @Override
    public SslStoreBundle getStores() {
        return null;
    }

    @Override
    public SslBundleKey getKey() {
        return null;
    }

    @Override
    public SslOptions getOptions() {
        return new SslOptions() {
            @Override
            public String[] getCiphers() {
                return sslFactory.getSslParameters().getCipherSuites();
            }

            @Override
            public String[] getEnabledProtocols() {
                return sslFactory.getSslParameters().getProtocols();
            }
        };
    }

    @Override
    public String getProtocol() {
        return "";
    }

    @Override
    public SslManagerBundle getManagers() {
        return new SslManagerBundle() {
            @Override
            public KeyManagerFactory getKeyManagerFactory() {
                return sslFactory.getKeyManagerFactory().get();
            }

            @Override
            public TrustManagerFactory getTrustManagerFactory() {
                return sslFactory.getTrustManagerFactory().get();
            }
        };
    }

    @Override
    public SSLContext createSslContext() {
        return sslFactory.getSslContext();
    }

}
