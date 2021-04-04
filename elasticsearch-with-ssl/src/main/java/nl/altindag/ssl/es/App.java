package nl.altindag.ssl.es;

import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.CertificateUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.Certificate;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        Path certificatePath = Paths.get("elasticsearch-with-ssl/elasticsearch-docker/ca.crt").normalize().toAbsolutePath();
        List<Certificate> certificates = CertificateUtils.loadCertificate(certificatePath);
        SSLFactory sslFactory = SSLFactory.builder()
                .withTrustMaterial(certificates)
                .build();

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "PleaseChangeMe"));

        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost", 9200, "https"))
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setSSLContext(sslFactory.getSslContext()).setDefaultCredentialsProvider(credentialsProvider));

        try(RestHighLevelClient client = new RestHighLevelClient(restClientBuilder)) {
            ClusterHealthResponse healthResponse = client.cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT);
            System.out.println(healthResponse);
        }
    }

}
