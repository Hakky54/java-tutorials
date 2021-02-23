package nl.altindag.server.controller;

import nl.altindag.server.model.SSLUpdateRequest;
import nl.altindag.server.service.SwappableSslService;
import nl.altindag.ssl.util.KeyManagerUtils;
import nl.altindag.ssl.util.KeyStoreUtils;
import nl.altindag.ssl.util.TrustManagerUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

@RestController
public class AdminController {

    private final SwappableSslService sslService;

    public AdminController(SwappableSslService sslService) {
        this.sslService = sslService;
    }

    @PostMapping(value = "/admin/ssl", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateKeyManager(@RequestBody SSLUpdateRequest request) throws IOException {
        try (InputStream keyStoreStream = new ByteArrayInputStream(request.getKeyStore());
             InputStream trustStoreStream = new ByteArrayInputStream(request.getTrustStore())) {

            KeyStore keyStore = KeyStoreUtils.loadKeyStore(keyStoreStream, request.getKeyStorePassword());
            X509ExtendedKeyManager keyManager = KeyManagerUtils.createKeyManager(keyStore, request.getKeyStorePassword());

            KeyStore trustStore = KeyStoreUtils.loadKeyStore(trustStoreStream, request.getTrustStorePassword());
            X509ExtendedTrustManager trustManager = TrustManagerUtils.createTrustManager(trustStore);

            sslService.updateKeyManager(keyManager);
            sslService.updateTrustManager(trustManager);
        }
    }

}
