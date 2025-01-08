package com.example.demo.model;

import lombok.Data;

@Data
public class Season {
    private int id;
    private String deviceIU;
    private String vehicleNo;
    private String effectiveDate;
    private String expiredDate;
    private String createDatetime;
    private String updateDatetime;
}
