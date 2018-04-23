package io.netty.messagepack.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.messagepack.dto.UserInfo;

import java.util.List;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    public EchoServerHandler(){
        System.out.println("EchoServerHandler 启动了");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        List<UserInfo> userInfos = (List<UserInfo>) msg;
        System.out.println("Server recieve the message : " + userInfos);
        ctx.writeAndFlush("消息已接收");
//        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Server catch exception " +cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
}
