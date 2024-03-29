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
package nl.altindag.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.altindag.ssl.SSLFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AppShould {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void updateServerCertificate() throws IOException, InterruptedException {
        assertServerCertificateExpirationDate("2029-05-02");

        SSLFactory sslFactory = createClientSllFactory();

        HttpClient httpClient = HttpClient.newBuilder()
                .sslParameters(sslFactory.getSslParameters())
                .sslContext(sslFactory.getSslContext())
                .build();

        byte[] identity = App.class.getClassLoader().getResourceAsStream("keystores/server/identity.jks").readAllBytes();
        byte[] truststore = App.class.getClassLoader().getResourceAsStream("keystores/server/truststore.jks").readAllBytes();
        SSLUpdateRequest sslUpdateRequest = new SSLUpdateRequest(identity, "secret".toCharArray(), truststore, "secret".toCharArray());

        String body = objectMapper.writeValueAsString(sslUpdateRequest);

        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .uri(URI.create("https://localhost:8443/admin/ssl"))
                .build();

        HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
        assertThat(response.statusCode()).isEqualTo(200);

        assertServerCertificateExpirationDate("2031-01-26");
    }

    private void assertServerCertificateExpirationDate(String date) throws IOException {
        URL helloEndpoint = new URL("https://localhost:8443/api/hello");
        HttpsURLConnection connection = (HttpsURLConnection) helloEndpoint.openConnection();
        connection.setSSLSocketFactory(createClientSllFactory().getSslSocketFactory());
        connection.connect();
        Certificate[] serverCertificates = connection.getServerCertificates();

        assertThat(serverCertificates).isNotEmpty();

        Certificate certificate = serverCertificates[0];
        if(certificate instanceof X509Certificate) {
            X509Certificate x509Certificate = (X509Certificate ) certificate;
            Date expirationDate = x509Certificate.getNotAfter();
            assertThat(expirationDate).isInSameDayAs(date);
        }
        connection.disconnect();
    }

    private SSLFactory createClientSllFactory() {
        return SSLFactory.builder()
                .withIdentityMaterial("keystores/admin/identity.jks", "secret".toCharArray())
                .withTrustMaterial("keystores/admin/truststore.jks", "secret".toCharArray())
                .build();
    }

    @SuppressWarnings("unused")
    static class SSLUpdateRequest {

        private byte[] keyStore;
        private char[] keyStorePassword;
        private byte[] trustStore;
        private char[] trustStorePassword;

        public SSLUpdateRequest() {}

        public SSLUpdateRequest(byte[] keyStore, char[] keyStorePassword, byte[] trustStore, char[] trustStorePassword) {
            this.keyStore = keyStore;
            this.keyStorePassword = keyStorePassword;
            this.trustStore = trustStore;
            this.trustStorePassword = trustStorePassword;
        }

        public byte[] getKeyStore() {
            return keyStore;
        }

        public void setKeyStore(byte[] keyStore) {
            this.keyStore = keyStore;
        }

        public char[] getKeyStorePassword() {
            return keyStorePassword;
        }

        public void setKeyStorePassword(char[] keyStorePassword) {
            this.keyStorePassword = keyStorePassword;
        }

        public byte[] getTrustStore() {
            return trustStore;
        }

        public void setTrustStore(byte[] trustStore) {
            this.trustStore = trustStore;
        }

        public char[] getTrustStorePassword() {
            return trustStorePassword;
        }

        public void setTrustStorePassword(char[] trustStorePassword) {
            this.trustStorePassword = trustStorePassword;
        }

    }

}
