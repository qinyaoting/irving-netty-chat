package com.example.demo.model;

import lombok.Data;

@Data
public class Settlement {
    private int id;
    private int exitId;
    private String stationName;
    private String sofAcquirer;
    public String sofName;
    public String sofTid;
    public String sofMid;
    public String sofSaleCount;
    public String sofSaleTotal;
    public String sofTopupCount;
    public String sofTopupTotal;

    private String      createDatetime;
    private String      updateDatetime;
    private int            serverSyncFlag;
    private String      serverSyncDatetime;
}
