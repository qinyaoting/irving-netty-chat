package com.example.demo.controller;

import com.example.demo.mapper.TransMapper;
import com.example.demo.model.Settlement;
import com.example.demo.model.Trans;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/carpark")
public class CarParkController {

    @Autowired
    private TransMapper transMapper;
    @PostMapping("/trans")
    public ResponseEntity<String> receiveTrans(@RequestBody List<Trans> trans) {
        int s = transMapper.insertTrans(trans);
        return ResponseEntity.ok(s + " Trans received"+ " at " + DateTime.now());
    }


    @PostMapping("/settlement")
    public ResponseEntity<String> receiveSettlement(@RequestBody List<Settlement> list) {
        try {
            int s = transMapper.insertSettlement(list);
            String serverSyncDatetime = list.get(0).getServerSyncDatetime();
            int cs = transMapper.makeSettlementsExpired(serverSyncDatetime);
            return ResponseEntity.ok(s + " Settlement received."+cs +" records expired, at " + DateTime.now() );
        } catch (Exception e) {
            log.error("receiveSettlement err:" + e.getMessage());
        }
        return ResponseEntity.ok("save data failed, maybe data format error");
    }
}
