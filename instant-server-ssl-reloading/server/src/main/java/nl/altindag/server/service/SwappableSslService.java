package nl.altindag.server.service;

import nl.altindag.ssl.util.KeyManagerUtils;
import nl.altindag.ssl.util.TrustManagerUtils;
import org.springframework.stereotype.Service;

import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;

@Service
public class SwappableSslService {

    private final X509ExtendedKeyManager swappableKeyManager;
    private final X509ExtendedTrustManager swappableTrustManager;

    public SwappableSslService(X509ExtendedKeyManager swappableKeyManager, X509ExtendedTrustManager swappableTrustManager) {
        this.swappableKeyManager = swappableKeyManager;
        this.swappableTrustManager = swappableTrustManager;
    }

    public void updateKeyManager(X509ExtendedKeyManager keyManager) {
        KeyManagerUtils.swapKeyManager(swappableKeyManager, keyManager);
    }

    public void updateTrustManager(X509ExtendedTrustManager trustManager) {
        TrustManagerUtils.swapTrustManager(swappableTrustManager, trustManager);
    }

}
