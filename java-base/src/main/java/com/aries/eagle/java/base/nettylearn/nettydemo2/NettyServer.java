package com.aries.eagle.java.base.nettylearn.nettydemo2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();     // boosGroup：对应IOServer.java中的接受新连接线程，主要负责创建新连接
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();   // workerGroup：对应IOClient.java中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理

        ServerBootstrap serverBootstrap = new ServerBootstrap();   // ServerBootstrap：创建了一个引导类ServerBootstrap, 引导我们进行服务端的启动工作，直接new出来开搞。
        serverBootstrap
                .group(boosGroup, workerGroup)                             // group：给引导类配置两大线程，这个引导类的线程模型也就定型了。
                .channel(NioServerSocketChannel.class)                     // NioServerSocketChannel：指定具体的I/O模型
                .childHandler(new ChannelInitializer<NioSocketChannel>() { // ChannelInitializer：主要就是定义后续每条连接的数据读写，业务处理逻辑
                    @Override
                    protected void initChannel(NioSocketChannel ch) {      // NioSocketChannel：Netty对NIO类型的连接的抽象
                    }
                });

        // serverBootstrap.bind(8000);
        bind(serverBootstrap, 1000);

        /*
         * childHandler()：用于指定处理新连接数据的读写处理逻辑
         * handler()：用于指定在服务端启动过程中的一些逻辑，通常情况下呢，我们用不着这个方法。
         */
        /*serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            protected void initChannel(NioServerSocketChannel ch) {
                System.out.println("服务端启动中");
            }
        });*/


        // serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer") // 给服务端的channel，也就是NioServerSocketChannel指定一些自定义属性，然后我们可以通过channel.attr()取出这个属性
        // 上面的代码我们指定我们服务端channel的一个serverName属性，属性值为nettyServer

        // serverBootstrap.childAttr(AttributeKey.newInstance(777"clientKey"), "clientValue")
        // childAttr可以给每一条连接指定自定义属性，然后后续我们可以通过channel.attr()取出该属性

        // serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true).childOption(ChannelOption.TCP_NODELAY, true)
        /*
         * childOption()可以给每条连接设置一些TCP底层相关的属性，比如上面，我们设置了三种TCP属性，
         * ChannelOption.SO_KEEPALIVE表示是否开启TCP底层心跳机制，true为开启
         * ChannelOption.TCP_NODELAY表示是否开始Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
         */

        // serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024) // 表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {       // 监听端口是否绑定成功
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }

}