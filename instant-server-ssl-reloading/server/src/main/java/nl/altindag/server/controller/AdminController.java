package nl.altindag.server.controller;

import nl.altindag.server.model.SSLUpdateRequest;
import nl.altindag.server.service.SwappableSslService;
import nl.altindag.ssl.SSLFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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

            SSLFactory sslFactory = SSLFactory.builder()
                    .withIdentityMaterial(keyStoreStream, request.getKeyStorePassword())
                    .withTrustMaterial(trustStoreStream, request.getTrustStorePassword())
                    .build();

            sslService.updateSslMaterials(sslFactory.getKeyManager().orElseThrow(), sslFactory.getTrustManager().orElseThrow());
        }
    }

}
