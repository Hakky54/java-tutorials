package nl.altindag.admin.model;

public class SSLUpdateRequest {

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
