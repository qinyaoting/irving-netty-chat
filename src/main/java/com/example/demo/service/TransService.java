package com.example.demo.service;

import com.example.demo.mapper.TransMapper;
import com.example.demo.model.DeviceStatus;
import com.example.demo.model.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public  class TransService {
    @Autowired
    private TransMapper transMapper;

    @Transactional
    public int addTrans(Trans trans) {
        return transMapper.addTrans(trans);
    }

    @Transactional
    public int insertTranList(@Param("trans") List<Trans> trans) {
        return transMapper.insertTranList(trans);
    }


    @Transactional
    public int addDeviceStatus(DeviceStatus deviceStatus) {
        return transMapper.insertDeviceStatus(deviceStatus);
    }
    @Transactional
    public int addDeviceStatusList(List<DeviceStatus> deviceStatusList) {
        return transMapper.insertDeviceStatusList(deviceStatusList);
    }
    public int saveApiPing(DeviceStatus deviceStatus) {
        return transMapper.insertDeviceStatus(deviceStatus);
    }
}