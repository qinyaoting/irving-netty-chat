package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.VehicleChargeClassMapper;
import com.example.demo.model.VehicleChargeClass;
import com.example.demo.processor.MsgProcessor;
import com.example.demo.service.UserService;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.json.JsonObject;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.*;

@Service
public class BinlogService {

    ///@Value("${mysql.password}")
    ///private String password;

    private LinkedBlockingQueue<String> binlogChanges = new LinkedBlockingQueue<>();
    MsgProcessor processor = new MsgProcessor();
    // 存储 tableId -> tableName 的映射关系
    private Map<Long, String> tableIdToNameMap = new HashMap<>();
    // 开始监听 binlog
    public void startListening() throws Exception {
        BinaryLogClient client = new BinaryLogClient("192.168.1.199", 3366, "root", "qyisno1forever@world");

//         设置 serverId，防止与其他连接冲突
        client.setServerId(1);

        // 注册 binlog 事件监听器
        client.registerEventListener(new BinaryLogClient.EventListener() {
            @Override
            public void onEvent(Event event) {
                EventHeaderV4 header = event.getHeader();

                // 处理 TableMap 事件，用于记录表的映射关系
                if (event.getData() instanceof TableMapEventData) {
                    TableMapEventData tableMapData = (TableMapEventData) event.getData();
                    tableIdToNameMap.put(tableMapData.getTableId(), tableMapData.getTable());
                }
                // 判断事件类型，处理不同的 binlog 事件
                if (event.getData() instanceof WriteRowsEventData) {
                     WriteRowsEventData data = (WriteRowsEventData) event.getData();
                    String tableName = tableIdToNameMap.get(data.getTableId());
                    int rowsCount = data.getRows().size();
                    String message = String.format("INSERT事件：表 %s 插入了 %d 行数据", tableName, rowsCount);
                    System.out.println(message);
                    binlogChanges.offer(message);
                    if (tableName.equals("t_operation_log")) {
                        List<Serializable[]> rows = data.getRows();
                        for (Serializable[] row : rows) {
                            //System.out.println(Joiner.on(",").join(row.to));
                            String operateFlag = row[2].toString();
                            String jsonData = new String((byte[]) row[6], StandardCharsets.UTF_8) ;
                            String[] destStr = row[5].toString().split("-");
                            Arrays.stream(destStr).forEach(dest ->processor.sendMsg(dest,operateFlag, jsonData));
                        }
                    }
                }
                else if (event.getData() instanceof UpdateRowsEventData) {
                    UpdateRowsEventData data = (UpdateRowsEventData) event.getData();
                    String tableName = tableIdToNameMap.get(data.getTableId());
                    int rowsCount = data.getRows().size();
                    String message = String.format("UPDATE事件：表 %s 更新了 %d 行数据", tableName, rowsCount);
                    binlogChanges.offer(message);
                }
                else if (event.getData() instanceof DeleteRowsEventData) {
                    DeleteRowsEventData data = (DeleteRowsEventData) event.getData();
                    String tableName = tableIdToNameMap.get(data.getTableId());
                    int rowsCount = data.getRows().size();
                    String message = String.format("DELETE事件：表 %s 删除了 %d 行数据", tableName, rowsCount);
                    binlogChanges.offer(message);
                }
            }
        });

        // 连接并开始监听 binlog
        client.connect();
    }

    // 获取最新的 binlog 数据变化
    public LinkedBlockingQueue<String> getBinlogChanges() {
        return binlogChanges;
    }
}
