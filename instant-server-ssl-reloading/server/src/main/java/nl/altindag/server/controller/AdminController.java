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
