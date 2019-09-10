package com.cherish.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    NettyServerSideHandler nettyServerSideHandler;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
       //ByteBuf buf = Unpooled.copiedBuffer("\r\n".getBytes());//拆包粘包定义结束字符串（第一种解决方案）
       //channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));//在管道中加入结束字符串
        channel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast(nettyServerSideHandler);
    }
}