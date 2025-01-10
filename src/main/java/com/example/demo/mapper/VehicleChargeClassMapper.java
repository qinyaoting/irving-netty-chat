package com.example.demo.mapper;

import com.example.demo.model.Holiday;
import com.example.demo.model.Season;
import com.example.demo.model.UserInfo;
import com.example.demo.model.VehicleChargeClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VehicleChargeClassMapper {
    List<VehicleChargeClass> queryVehicleClassList();
    List<Season> querySeasonList();
    List<Holiday> queryHolidayList(String year);

}
