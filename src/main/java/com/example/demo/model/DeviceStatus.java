package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceStatus {
    private transient int  id;
    private String stationName;
    private String deviceName;
    private String event;
    private String pingDatetime;
    private String attachJson;
    private String errorMsg;
    private String remark;
    private int sid;
    private String createDatetime;
    private String updateDatetime;
}
