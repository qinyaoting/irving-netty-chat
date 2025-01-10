package com.example.demo.handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.Trans;
import com.example.demo.processor.MsgProcessor;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ChatServerHandler  extends SimpleChannelInboundHandler<String> {

    private MsgProcessor processor = new MsgProcessor();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String str = String.format("客户端上线 channelId:%s, ip:%s",channel.id(),channel.localAddress(),channel.remoteAddress());
        System.out.println("channel: " + JSON.toJSONString(channel));
    }

    /**
     * 业务处理
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel ch = ctx.channel();
        String str = msg.contains("\n")?msg.replace("\n", ""):msg;
        System.out.println(String.format("server get ip:%s  ---- msg:%s ", ch.remoteAddress(), str));
        processor.dealMsg(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
        ctx.channel().close();
    }

    /**
     * 客户端程序关闭则移除对应的channel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel: " + JSON.toJSONString(ctx.channel()));
        processor.removeChannelSendMsg(ctx.channel());
    }
}
