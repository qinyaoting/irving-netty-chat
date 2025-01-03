package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import com.example.demo.model.VehicleChargeClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VehicleChargeClassMapper {
    List<VehicleChargeClass> queryVehicleClassWithIU();

}
