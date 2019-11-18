package com.kristin.firstexample.chatdemo;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: Kristin
 * @Date: 2019/11/18 23:21
 * @Comment：
 */
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println(msg);
    }
}
