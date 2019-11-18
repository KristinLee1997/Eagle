package com.kristin.firstexample.chatdemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: Kristin
 * @Date: 2019/11/17 22:43
 * @Comment： server对应abc三个客户端，
 * 当a与服务器建立好连接时，服务器端会打印日志，服务器端与客户端a建立好连接
 * 当b与服务器建立好连接时，服务器端会打印日志，服务器端与客户端b建立好连接，同时服务器端通知其他与服务器端建立好连接的客户端
 * 实现消息的广播
 */
public class MyChatServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChatServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
