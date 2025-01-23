package com.example.demo.model;

import lombok.Data;

@Data
public class VehicleCharge {
    private int id;
    private String vehicleType;
    private String vehicleDesc;
    private String iuPrefix;
    private String createDatetime;
    private String updateDatetime;
    private int hourCharge;
    private int taxRate;
}