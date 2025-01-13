package com.example.demo.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Trans implements Serializable {
    // ==========for common===============
    private long id;

    private String createDatetime;
    //private int     serverSyncFlag;
    //private String  serverSyncDatetime;
    private String deviceIU;
    private String cardCan;
    private String  cardType;
    private String enterDatetime;
    private int     hourRate;                       // 1小时1.2刀 1小时120cent       //=== init by entry
    private String  vehicleType;                // 车辆类型Car|Lorry|PoliceCar|Motorcycle|Ambulance     //=== init by entry
    private String  payType;                      // 支付方式season|hourly          //=== init by entry
    private int     taxRate;                                                                               //=== init by entry
    private String entryGateNum;                                                                //=== init by entry
    private int     payStatus;                      //-1未支付, 0结算成功,未支付, 1支付成功, 2月付不用支付,3免費gap space時段进入驶出 (entry -1|2) (exit 0|1|3)
    // ==========for entry=================
    //private int            exitSyncFlag;
    //private String      exitSyncDatetime;
    // ==========for exit===================
    private long exitId;
    private long    entryId;                     //  数据同步到出口时候, 会使用该字段, 存储的是入口的id,
    private String exitGateNum;
    private String leaveDatetime;
    private int     totalSecond;
    private String  cost;
    private String  taxCost;
    private String  totalCost;
    private String payCard;
    private String attachJson;
    private String payDatetime;
    private long autoPaymentPayloadId;
    private long receiptId;
}
