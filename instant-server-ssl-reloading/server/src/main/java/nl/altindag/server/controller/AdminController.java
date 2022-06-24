package nl.altindag.server.controller;

import nl.altindag.server.model.SSLUpdateRequest;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.SSLFactoryUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class AdminController {

    private final SSLFactory baseSslFactory;

    public AdminController(SSLFactory baseSslFactory) {
        this.baseSslFactory = baseSslFactory;
    }

    @PostMapping(value = "/admin/ssl", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateKeyManager(@RequestBody SSLUpdateRequest request) throws IOException {
        try (InputStream keyStoreStream = new ByteArrayInputStream(request.getKeyStore());
             InputStream trustStoreStream = new ByteArrayInputStream(request.getTrustStore())) {

            SSLFactory updatedSslFactory = SSLFactory.builder()
                    .withIdentityMaterial(keyStoreStream, request.getKeyStorePassword())
                    .withTrustMaterial(trustStoreStream, request.getTrustStorePassword())
                    .build();

            SSLFactoryUtils.reload(baseSslFactory, updatedSslFactory);
        }
    }

}
