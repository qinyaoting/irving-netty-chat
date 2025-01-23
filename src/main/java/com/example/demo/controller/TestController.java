package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<Map<String, Object>> handlePost(@RequestBody Map<String, Object> payload) {
        // 打印接收到的 JSON 数据
        System.out.println("Received JSON: " + payload);

        // 构建响应数据
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("received", payload);

        // 返回响应，Spring Boot 自动将 Map 转换为 JSON 格式
        return ResponseEntity.ok(response);
    }
}
