package io.netty.messagepack.client;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.messagepack.dto.UserInfo;

/**
 * @author yunlang
 * 客户端信道处理器
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int i = 0;
        for (; i < sendNumber; i++) {
            UserInfo info = getUserInfo(i);
            String s = JSON.toJSONString(info);
            System.out.println("Client send message : " + JSON.toJSONString(info));
            byte[] bytes = s.getBytes();
            ByteBuf buffer = Unpooled.buffer(bytes.length);
            buffer.writeBytes(bytes);
            System.out.println("bytebuf : " + buffer);
            ctx.write(info);
        }
        ctx.flush();
        System.out.println("client send message finished!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client recieve the message : " + msg);
//        ctx.write(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Client catch exception " + cause.getMessage());
        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private UserInfo getUserInfo(int i) {
        UserInfo info = new UserInfo();
        info.setAge(i);
        info.setGender(i % 2 == 0 ? "男" : "女");
        info.setName("ABC---->" + i);
        return info;
    }
}
