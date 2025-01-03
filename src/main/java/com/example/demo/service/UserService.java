package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.VehicleChargeClassMapper;
import com.example.demo.model.User;
import com.example.demo.model.VehicleChargeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class UserService {
    private static VehicleChargeClassMapper vehicleChargeClassMapper = null;

    public UserService(VehicleChargeClassMapper vehicleChargeClassMapper) {
        this.vehicleChargeClassMapper = vehicleChargeClassMapper;
    }

    public static List<VehicleChargeClass> getAllVehicleCharge() {
        return vehicleChargeClassMapper.queryVehicleClassWithIU();
    }
}