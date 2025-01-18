package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.TransMapper;
import com.example.demo.model.DeviceStatus;
import com.example.demo.model.Settlement;
import com.example.demo.model.Trans;
import com.google.common.collect.Lists;
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
    public ResponseEntity<String> receiveTrans(@RequestBody List<Trans> list) {
        try {
            int s = transMapper.insertTrans(list);
            return ResponseEntity.ok(s + " Trans received"+ " at " + DateTime.now());
        } catch (Exception e) {
            log.error("receiveTrans err:" + e.getMessage()+"json:" + JSON.toJSONString(list));
        }
        return ResponseEntity.ok("save data failed, maybe data format error");
    }

    @PostMapping("/settlement")
    public ResponseEntity<String> receiveSettlement(@RequestBody List<Settlement> list) {
        try {
            int s = transMapper.insertSettlement(list);
            String serverSyncDatetime = list.get(0).getServerSyncDatetime();
            int cs = transMapper.makeSettlementsExpired(serverSyncDatetime);
            return ResponseEntity.ok(s + " Settlement received."+cs +" records expired, at " + DateTime.now() );
        } catch (Exception e) {
            log.error("receiveSettlement err:" + e.getMessage() +"json:" + JSON.toJSONString(list));
        }
        return ResponseEntity.ok("save data failed, maybe data format error");
    }

    @PostMapping("/device_status")
    public ResponseEntity<String> receiveDeviceStatusList(@RequestBody List<DeviceStatus> deviceStatusList) {
        try {
            int cs = transMapper.insertDeviceStatusList(deviceStatusList);
            return ResponseEntity.ok("success:"+cs );
        } catch (Exception e) {
            log.error("receiveDeviceStatus err:" + e.getMessage() +"json:" + JSON.toJSONString(deviceStatusList));
        }
        return ResponseEntity.ok("failed:data format error?");
    }


}
