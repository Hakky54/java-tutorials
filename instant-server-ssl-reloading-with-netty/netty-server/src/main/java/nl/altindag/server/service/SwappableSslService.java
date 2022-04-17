package nl.altindag.server.service;

import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.KeyManagerUtils;
import nl.altindag.ssl.util.SSLSessionUtils;
import nl.altindag.ssl.util.TrustManagerUtils;

import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;

public class SwappableSslService {

    private final SSLSessionContext sslSessionContext;
    private final X509ExtendedKeyManager swappableKeyManager;
    private final X509ExtendedTrustManager swappableTrustManager;

    public SwappableSslService(SSLFactory sslFactory) {
        this.sslSessionContext = sslFactory.getSslContext().getServerSessionContext();
        this.swappableKeyManager = sslFactory.getKeyManager().orElseThrow();
        this.swappableTrustManager = sslFactory.getTrustManager().orElseThrow();
    }

    public void updateSslMaterials(X509ExtendedKeyManager keyManager, X509ExtendedTrustManager trustManager) {
        KeyManagerUtils.swapKeyManager(swappableKeyManager, keyManager);
        TrustManagerUtils.swapTrustManager(swappableTrustManager, trustManager);
        SSLSessionUtils.invalidateCaches(sslSessionContext);
    }

}
