package com.kristin.firstexample.httpdemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author: Kristin
 * @Date: 2019/11/10 14:34
 * @Comment：
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // pipeline中添加的组件不要写成singleton的，尽量保证每次都是新实例
        pipeline.addLast("httpServerCodec", new HttpServerCodec()); // HttpServerCodec是Netty进行http编程的重要组件
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());

    }
}
