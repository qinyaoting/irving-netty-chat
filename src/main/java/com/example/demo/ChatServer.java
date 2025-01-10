package com.example.demo;

import com.example.demo.handler.ChatServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @author luojun
 * @version 1.0.0
 * @ClassName ChatServer
 * @Description chat服务
 * @createTime 2021/11/14 3:06
 */
@Slf4j
@Component
public class ChatServer implements DisposableBean {

    private int port = 8060; // netty服务端口

    private EventLoopGroup bossGroup; // 主线程池
    private EventLoopGroup workerGroup; // 从线程池
    private ServerBootstrap serverBootstrap; // 服务端启动

    public ChatServer() {
        // 主从线程模式的线程池
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                // android app重启,连接netty  server address already use 怎么解决
                //x.childOption(ChannelOption.SO_REUSEADDR, true)
                .childHandler(new ChatServerChannelInitializer());
    }

    public void start() {
        try {
            serverBootstrap.bind(port).sync();
            log.info("netty websocket server 启动完毕，对应端口：{}", this.port);
        } catch (InterruptedException e) {
            close();
            log.error("netty websocket server 启动失败...异常原因：{}", e.getMessage());
        }
    }

    public void close() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    /**
     * 优雅的关闭netty，所以实现DisposableBean
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        close();
    }
}