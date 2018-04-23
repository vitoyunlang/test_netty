package io.netty.protobuf.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 * @author yunlang
 */
public class ProtoBufServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf req =(ByteBuf) msg;
        byte[] bytes = new byte[req.readableBytes()];
        req.readBytes(bytes);
        String request = new String(bytes);
        System.out.println("Server receive message " + request);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("Server catch exception " +cause.getMessage());
        ctx.flush();
    }
}
