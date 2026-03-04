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
package nl.altindag.client.config;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.NettySslContextChannelCredentials;
import io.netty.handler.ssl.SslContext;
import nl.altindag.grpc.HelloServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.ChannelCredentialsProvider;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class StubConfig {

    @Bean
    public ChannelCredentialsProvider grpcChannelCredentialsProvider(SslContext sslContext) {
        return channelName -> NettySslContextChannelCredentials.create(sslContext);
    }

    @Bean
    public ManagedChannel localChannel(GrpcChannelFactory channels) {
        return channels.createChannel("localhost:8443");
    }

    @Bean
    public HelloServiceGrpc.HelloServiceBlockingStub stub(ManagedChannel channel) {
        return HelloServiceGrpc.newBlockingStub(channel);
    }

}
