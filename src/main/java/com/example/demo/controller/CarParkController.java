package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.TransMapper;
import com.example.demo.model.DeviceStatus;
import com.example.demo.model.Settlement;
import com.example.demo.model.Trans;
import com.example.demo.service.TransService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/carpark")
public class CarParkController {

    @Autowired
    private TransMapper transMapper;

    @Autowired
    private TransService transService;

    @PostMapping("/trans")
    @ResponseBody
    public ResponseEntity<String> receiveTrans(@RequestBody List<Trans> list) {
        try {
            int s = transService.insertTranList(list);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
        } catch (Exception e) {
            log.error("receiveTrans err:" + e.getMessage()+"json:" + JSON.toJSONString(list));
        }
        return ResponseEntity.ok("failed");
    }

    @PostMapping(value ="/settlement")
    @ResponseBody
    public ResponseEntity<String>  receiveSettlement(@RequestBody List<Settlement> list) {
        try {
            int s = transMapper.insertSettlement(list);
            String serverSyncDatetime = list.get(0).getServerSyncDatetime();
            int cs = transMapper.makeSettlementsExpired(serverSyncDatetime);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
        } catch (Exception e) {
            log.error("receiveSettlement err:" + e.getMessage() +"json:" + JSON.toJSONString(list));
        }
        return ResponseEntity.ok("failed");
    }

    @PostMapping("/device_status")
    @ResponseBody
    public ResponseEntity<String> receiveDeviceStatusList(@RequestBody List<DeviceStatus> deviceStatusList) {
        try {
            int cs = transService.addDeviceStatusList(deviceStatusList);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("success");
        } catch (Exception e) {
            log.error("receiveDeviceStatus err:" + e.getMessage() +"json:" + JSON.toJSONString(deviceStatusList));
        }
        return ResponseEntity.ok("failed");
    }


}
