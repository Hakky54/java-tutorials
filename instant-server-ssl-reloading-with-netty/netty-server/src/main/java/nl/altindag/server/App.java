package nl.altindag.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import nl.altindag.server.config.ServerInitializer;
import nl.altindag.server.service.FileBasedSslUpdateService;
import nl.altindag.server.service.SwappableSslService;
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

            var swappableSslService = new SwappableSslService(sslFactory);
            var sslUpdateService = new FileBasedSslUpdateService(swappableSslService);

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
