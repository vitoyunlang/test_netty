package io.netty.protobuf.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yunlang
 */
public class ProtoBufClient {
    public static final int defaultPort = 8080;
    public static final String defaultHost = "localhost";

    public static void main(String[] args) throws Exception {
        int port;
        String host;
        if (args!= null && args.length>1) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            host = defaultHost;
            port = defaultPort;
        }
        new ProtoBufClient().connect(host,port);
    }

    public void connect(String host,int port) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ProtoBufClientHandler());
                    }
                });
        try {
            System.out.println("Client connect to " + host + ":" + port);
            ChannelFuture f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
