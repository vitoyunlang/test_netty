package io.netty.messagepack.coder;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @author yunlang
 * MessagePack 编码器
 */
public class MsgpackEncoder extends MessageToByteEncoder{

    public static volatile int count = 0;

    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        System.out.println("MsgpackEncoder start to work ,And count : " + ++count);
        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(o);
        byteBuf.writeBytes(bytes);
    }
}
