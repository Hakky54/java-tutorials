package nl.altindag.server.model;

import javax.persistence.*;

@Entity
@Table(name = "SSL_MATERIAL")
public class SSLMaterial {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String identityContent;
    private String identityPassword;
    private String trustedCertificates;

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
