package io.netty.messagepack.coder;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import java.util.List;

/**
 * @author yunlang
 * MessagePack 解码器
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

    public static volatile int count = 0;

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MsgpackDecoder start and convert message");
        final byte[] array;
        final int length = byteBuf.readableBytes();
        array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), array, 0, length);
        MessagePack messagePack = new MessagePack();
        Value read = messagePack.read(array);
        System.out.println("MsgpackDecoder decode count : " + ++count);
        list.add(read);
    }
}
