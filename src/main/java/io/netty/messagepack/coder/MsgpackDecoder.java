package io.netty.messagepack.coder;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;
import org.msgpack.type.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yunlang
 * MessagePack 解码器
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final byte[] array;
        final int length = byteBuf.readableBytes();
        array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(),array);
        MessagePack messagePack = new MessagePack();
        Value read = messagePack.read(array);
        System.out.println("MsgpackDecoder decode "+JSON.toJSONString(read));
        list.add(read);
    }
}
