package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.TransMapper;
import com.example.demo.model.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trans")
public class TransController {

    @Autowired
    private TransMapper transMapper;
    @PostMapping("/receive")
    public ResponseEntity<String> receiveJson(@RequestBody List<Trans> trans) {
        System.out.println( "===============" + JSON.toJSONString(trans.size()));
        int s = transMapper.insertTrans(trans);
        return ResponseEntity.ok("JSON received successfully!" + s);
    }
}
