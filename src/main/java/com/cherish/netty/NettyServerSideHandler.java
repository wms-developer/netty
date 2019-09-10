package com.cherish.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NettyServerSideHandler extends ChannelHandlerAdapter {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端接入进来");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("server:" + body);//前面已经定义了接收为字符串，这里直接接收字符串就可以
        //服务端给客户端的响应
        String response = "hi client! i'm server";//发送的数据以定义结束的字符串结尾
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));//发送必须还是ByteBuf类型
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}