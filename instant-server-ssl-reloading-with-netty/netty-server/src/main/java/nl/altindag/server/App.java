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

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import nl.altindag.server.config.ServerInitializer;
import nl.altindag.server.service.FileBasedSslUpdateService;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.NettySslUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private final SSLFactory sslFactory;

    public App() {
        sslFactory = SSLFactory.builder()
                .withSwappableTrustMaterial()
                .withIdentityMaterial("identity.jks", "secret".toCharArray())
                .withSwappableIdentityMaterial()
                .withTrustMaterial("truststore.jks", "secret".toCharArray())
                .build();
    }

    public void run() throws Exception {
        var bossGroup = new NioEventLoopGroup(1);
        var workerGroup = new NioEventLoopGroup();

        var sslContext = NettySslUtils.forServer(sslFactory)
                .build();

        try {
            var serverBootstrap = new ServerBootstrap();
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            serverBootstrap.group(bossGroup, workerGroup)
                    .channelFactory(NioServerSocketChannel::new)
                    .childHandler(new ServerInitializer(sslContext));

            var httpChannel = serverBootstrap.bind(8443).sync();

            LOGGER.info("Sever started on https://localhost:8443");

            var sslUpdateService = new FileBasedSslUpdateService(sslFactory);

            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(sslUpdateService::updateSslMaterial, 1, 1, TimeUnit.MINUTES);
            LOGGER.info("Checking every minute for changes on the keystore and truststore files");

            httpChannel.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new App().run();
    }

}
