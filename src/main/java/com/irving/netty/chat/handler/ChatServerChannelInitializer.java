package com.irving.netty.chat.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author luojun
 * @version 1.0.0
 * @ClassName ChatServerChannelInitializer
 * @Description 初始化所有的ChannelHandler到 ChannelPipeline
 * @createTime 2021/11/14 2:52
 */
public class ChatServerChannelInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
            // 针对客户端，如果在1分钟时没有向服务端发送读写心跳(ALL)，则主动断开
            // 如果是写空闲，不处理
            pipeline.addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));//5s未发送数据，回调userEventTriggered
            pipeline.addLast(new HeartBeatHandler());
            pipeline.addLast(new ChatServerHandler());
    }
}