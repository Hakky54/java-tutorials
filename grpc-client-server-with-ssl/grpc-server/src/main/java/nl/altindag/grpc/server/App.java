package nl.altindag.grpc.server;

import io.grpc.Server;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyServerBuilder;
import io.netty.handler.ssl.SslContext;
import nl.altindag.grpc.server.service.HelloServiceImpl;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.NettySslUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final int PORT = 8443;

    private Server server;

    private void start() throws IOException {
        SSLFactory sslFactory = SSLFactory.builder()
                .withIdentityMaterial("server/identity.jks", "secret".toCharArray())
                .withTrustMaterial("server/truststore.jks", "secret".toCharArray())
                .withNeedClientAuthentication()
                .build();

        SslContext sslContext = GrpcSslContexts.configure(NettySslUtils.forServer(sslFactory)).build();

        server = NettyServerBuilder.forPort(PORT)
                .addService(new HelloServiceImpl())
                .sslContext(sslContext)
                .build()
                .start();

        LOGGER.info("Server started, listening on " + PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.warn("*** shutting down gRPC server since JVM is shutting down");
            App.this.stop();
            LOGGER.warn("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final App server = new App();
        server.start();
        server.blockUntilShutdown();
    }

}
