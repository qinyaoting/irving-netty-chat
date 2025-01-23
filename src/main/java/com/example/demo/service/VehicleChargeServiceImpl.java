package com.example.demo.service;
import com.example.demo.mapper.VehicleChargeMapper;
import com.example.demo.model.VehicleCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleChargeServiceImpl implements VehicleChargeService {
    @Autowired
    private VehicleChargeMapper mapper;

    @Override
    public List<VehicleCharge> getAll(int page, int size) {
        int offset = (page - 1) * size;
        return mapper.getAll(offset, size);
    }

    @Override
    public int count() {
        return mapper.count();
    }

    @Override
    public void add(VehicleCharge vehicleCharge) {
        mapper.add(vehicleCharge);
    }

    @Override
    public void update(VehicleCharge vehicleCharge) {
        mapper.update(vehicleCharge);
    }

    @Override
    public void delete(int id) {
        mapper.delete(id);
    }
}
