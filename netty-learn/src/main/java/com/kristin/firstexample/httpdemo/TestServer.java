package com.kristin.firstexample.httpdemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: Kristin
 * @Date: 2019/11/10 14:27
 * @Comment：
 */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();       // 事件循环组bossGroup：获取连接的死循环
        EventLoopGroup wokerGroup = new NioEventLoopGroup();      // 事件循环组wokerGroup：处理连接的死循环

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();    // 服务端启动类
            serverBootstrap.group(bossGroup, wokerGroup)
                    .channel(NioServerSocketChannel.class)              // 使用反射的方式创建NioServerSocketChannel
                    .childHandler(new TestServerInitializer());         // 定义子处理器：在channel被创建成功后，自动执行TestServerInitializer类中定义的代码逻辑

            ChannelFuture channelFuture = serverBootstrap.bind(10014).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }
    }
}
