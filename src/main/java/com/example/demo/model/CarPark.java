package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarPark {
    private transient int  id;
    private String carparkName;
    private int hourlyCount;
    private int seasonCount;
    private int totalLots;
    private int lotsAvailable;
    private String createDatetime;
    private String updateDatetime;
    private int serverSyncFlag;
    private String remark;
}
