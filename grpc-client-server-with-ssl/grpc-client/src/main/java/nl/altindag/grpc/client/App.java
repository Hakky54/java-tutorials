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
package nl.altindag.grpc.client;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;
import nl.altindag.grpc.HelloRequest;
import nl.altindag.grpc.HelloResponse;
import nl.altindag.grpc.HelloServiceGrpc;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.netty.util.NettySslUtils;
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
        HelloResponse response = stub.hello(request);

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
