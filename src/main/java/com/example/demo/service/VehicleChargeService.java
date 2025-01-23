package com.example.demo.service;

import com.example.demo.model.VehicleCharge;

import java.util.List;

public interface VehicleChargeService {
    List<VehicleCharge> getAll(int page, int size);
    int count();
    void add(VehicleCharge vehicleCharge);
    void update(VehicleCharge vehicleCharge);
    void delete(int id);
}
