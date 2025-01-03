package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("com.example.demo.mapper")
@SpringBootApplication
public class IrvingNettyChatApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IrvingNettyChatApplication.class, args);
        BinlogService binlog = new BinlogService();
        binlog.startListening();
    }
}