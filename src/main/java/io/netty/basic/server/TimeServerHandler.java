package io.netty.basic.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
////        String body = new String(req,"Utf-8");
//        System.out.println("the time server receive order : "+body);

        //测试TCP粘包
//        String body = new String(req, "Utf-8").substring(0,req.length-System.getProperty("line.separator").length());
//        System.out.println("the time server receive order : " + body + " ; the counter is : " + ++counter);
//        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";

        //解决粘包
//        String body = new String(req, "Utf-8");
        String body = (String) msg;
        System.out.println("the time server receive order : " + body + " ; the counter is : " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";
        currentTime = currentTime+System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(io.netty.channel.ChannelHandlerContext ctx) throws java.lang.Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(io.netty.channel.ChannelHandlerContext ctx, java.lang.Throwable cause) throws java.lang.Exception {
        System.out.println("Server catch exception " +cause.getMessage());
        ctx.close();
    }
}
