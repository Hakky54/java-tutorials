package nl.altindag.grpc.client;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;
import nl.altindag.grpc.HelloRequest;
import nl.altindag.grpc.HelloResponse;
import nl.altindag.grpc.HelloServiceGrpc;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.NettySslUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private final HelloServiceGrpc.HelloServiceBlockingStub stub;

    public App(Channel channel) {
        stub = HelloServiceGrpc.newBlockingStub(channel);
    }

    public void hello(String name) {
        LOGGER.info("Will try to greet the server as {} ...", name);
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloResponse response;
        try {
            response = stub.hello(request);
        } catch (StatusRuntimeException e) {
            LOGGER.warn("RPC failed: {}", e.getStatus());
            return;
        }

        LOGGER.info("Server response: {}", response.getMessage());
    }


    public static void main(String[] args) throws SSLException {
        SSLFactory sslFactory = SSLFactory.builder()
                .withIdentityMaterial("client/identity.jks", "secret".toCharArray())
                .withTrustMaterial("client/truststore.jks", "secret".toCharArray())
                .withDefaultTrustMaterial()
                .build();

        SslContext sslContext = GrpcSslContexts.configure(NettySslUtils.forClient(sslFactory)).build();

        ManagedChannel channel = NettyChannelBuilder.forAddress("localhost", 8443)
                .sslContext(sslContext)
                .build();

        App app = new App(channel);
        app.hello("John");

        channel.shutdown();
    }

}
