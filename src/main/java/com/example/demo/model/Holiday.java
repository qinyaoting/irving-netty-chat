package com.example.demo.model;

import lombok.Data;

@Data
public class Holiday {
    private int id;
    private String holiday;
    private String holidayName;
    private String year;
    private String createDatetime;
    private String updateDatetime;
}
