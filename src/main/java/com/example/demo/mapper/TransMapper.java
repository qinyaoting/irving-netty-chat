package com.example.demo.mapper;

import com.example.demo.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TransMapper {
    List<Trans> queryTransList();

    UserInfo queryTransById(Integer id);

    int addTrans(Trans trans);

    int updateTrans(Trans trans);

    int deleteTrans(Integer id);

    List<VehicleChargeClass> queryVehicleClassList();

    int insertTrans(@Param("trans") List<Trans> trans);

    int insertSettlement(@Param("settlement") List<Settlement> settlement);

    int insertDeviceStatus(@Param("deviceStatus") DeviceStatus deviceStatus);

    int insertDeviceStatusList(@Param("deviceStatusList") List<DeviceStatus> deviceStatusList);

    int makeSettlementsExpired(String serverSyncDatetime);
}
