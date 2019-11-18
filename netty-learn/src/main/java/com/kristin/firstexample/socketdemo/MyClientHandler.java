package com.kristin.firstexample.socketdemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @Author: Kristin
 * @Date: 2019/11/10 17:37
 * @Comment：
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output: " + msg);

        ctx.writeAndFlush("from client: " + LocalDateTime.now());
    }

    @Override
    /**
     * 当通道已经连接好了时，会自动回调
     * 如果客户端handler不加此方法，会导致服务器端和客户端都在等在响应，但是没有人触发响应
     * 此方法目的在于触发请求
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自于客户端的问候");// 当客户端与服务端建立好连接后，客户端立刻会向服务器端发送一条数据
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
