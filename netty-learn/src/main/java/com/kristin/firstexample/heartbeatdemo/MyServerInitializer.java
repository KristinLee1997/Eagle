package com.kristin.firstexample.heartbeatdemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Kristin
 * @Date: 2019/11/18 23:47
 * @Comment：
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(5, 7, 3, TimeUnit.SECONDS));  // 空闲检测的处理器：一定时间内没有读或者没有写，就会触发IdleEvent事件
        pipeline.addLast(new MyServerHandler());
    }
}
