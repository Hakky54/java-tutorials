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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private final SSLFactory baseSslFactory;

    public AdminController(SSLFactory baseSslFactory) {
        this.baseSslFactory = baseSslFactory;
    }

    @PostMapping(value = "/admin/ssl", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateSslMaterial(@RequestBody SSLUpdateRequest request) throws IOException {
        try (InputStream keyStoreStream = new ByteArrayInputStream(request.keyStore());
             InputStream trustStoreStream = new ByteArrayInputStream(request.trustStore())) {

            SSLFactory updatedSslFactory = SSLFactory.builder()
                    .withIdentityMaterial(keyStoreStream, request.keyStorePassword())
                    .withTrustMaterial(trustStoreStream, request.trustStorePassword())
                    .build();

            SSLFactoryUtils.reload(baseSslFactory, updatedSslFactory);
            LOGGER.info("Updated server ssl material");
        }
    }

}
