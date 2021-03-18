package nl.altindag.server.service;

import nl.altindag.ssl.util.KeyManagerUtils;
import nl.altindag.ssl.util.SSLSessionUtils;
import nl.altindag.ssl.util.TrustManagerUtils;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;

@Service
public class SwappableSslService {

    private final SSLSessionContext sslSessionContext;
    private final X509ExtendedKeyManager swappableKeyManager;
    private final X509ExtendedTrustManager swappableTrustManager;

    public SwappableSslService(SSLSessionContext sslSessionContext,
                               X509ExtendedKeyManager swappableKeyManager,
                               X509ExtendedTrustManager swappableTrustManager) {

        this.sslSessionContext = sslSessionContext;
        this.swappableKeyManager = swappableKeyManager;
        this.swappableTrustManager = swappableTrustManager;
    }

    public void updateSslMaterials(X509ExtendedKeyManager keyManager, X509ExtendedTrustManager trustManager) {
        KeyManagerUtils.swapKeyManager(swappableKeyManager, keyManager);
        TrustManagerUtils.swapTrustManager(swappableTrustManager, trustManager);
        SSLSessionUtils.invalidateCaches(sslSessionContext);
    }

}
