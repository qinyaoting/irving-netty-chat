package com.irving.netty.chat;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * @author luojun
 * @version 1.0.0
 * @ClassName NettyBooter
 * @Description 自定义发布事件动作，启动ChatServer
 * @createTime 2021/11/14 3:23
 */
@Slf4j
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ChatServer chatServer;

    /**
     * 当所有的bean都已经处理完毕之后，spring ioc容器会有一个发布事件动作
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        chatServer.start();
        log.info("ChatServer启动");
    }
}