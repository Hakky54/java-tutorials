package nl.altindag.server.config;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import nl.altindag.server.handler.ServerHandler;


public class ServerInitializer extends ChannelInitializer<Channel> {

    private final SslContext sslContext;

    public ServerInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addFirst("ssl", new SslHandler(sslContext.newEngine(channel.alloc())))
                .addLast(new HttpServerCodec())
                .addLast(new ServerHandler());
    }

}
