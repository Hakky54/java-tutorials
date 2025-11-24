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
