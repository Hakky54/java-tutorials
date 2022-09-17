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
package nl.altindag.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "SSL_MATERIAL")
public class SSLMaterial {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Timestamp updatedAt;
    private String identityContent;
    private String identityPassword;
    private String trustedCertificates;

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp createdAt) {
        this.updatedAt = createdAt;
    }

    public String getIdentityContent() {
        return identityContent;
    }

    public void setIdentityContent(String identityContent) {
        this.identityContent = identityContent;
    }

    public String getIdentityPassword() {
        return identityPassword;
    }

    public void setIdentityPassword(String identityPassword) {
        this.identityPassword = identityPassword;
    }

    public String getTrustedCertificates() {
        return trustedCertificates;
    }

    public void setTrustedCertificates(String trustedCertificates) {
        this.trustedCertificates = trustedCertificates;
    }
}
