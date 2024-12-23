package com.irving.netty.chat.handler;

import com.irving.netty.chat.processor.MsgProcessor;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author luojun
 * @version 1.0.0
 * @ClassName ChatServerHandler
 * @Description 处理消息的handler
 * @createTime 2021/11/14 3:01
 */
public class ChatServerHandler  extends SimpleChannelInboundHandler<String> {

    private MsgProcessor processor = new MsgProcessor();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端 " + channel.remoteAddress() + "上线\n");
    }

    /**
     * 业务处理
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
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
        processor.removeChannelSendMsg(ctx.channel());
    }
}
