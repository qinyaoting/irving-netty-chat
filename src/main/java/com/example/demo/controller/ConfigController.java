package com.example.demo.controller;

import com.example.demo.mapper.VehicleChargeClassMapper;
import com.example.demo.model.Holiday;
import com.example.demo.model.Season;
import com.example.demo.model.VehicleChargeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private VehicleChargeClassMapper vehicleChargeMapper;;
    @GetMapping("/vehicle_charge")
    public List<VehicleChargeClass> getAllVehicleClass() {
        return vehicleChargeMapper.queryVehicleClassList();
    }

    @GetMapping("/timestamp")
    public String getSystemTimestamp() {
        return ""+System.currentTimeMillis();
    }

    @GetMapping("/holiday")
    public String getCurYearHoliday() {
        return ""+System.currentTimeMillis();
    }

    @GetMapping("/season")
    public List<Season> getSeasonList() {
        return vehicleChargeMapper.querySeasonList();
    }

    @GetMapping("/holiday/{year}")
    public List<Holiday> getHolidayList(@PathVariable("year") String year) {
        return vehicleChargeMapper.queryHolidayList(year);
    }
}
