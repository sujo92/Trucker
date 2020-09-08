package com.egen.VehicleReading.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class Alert {
    private String id;
    private String vin;
    private String priority;
    private Timestamp alertTime;
    private String alertdesc;

    public Alert(){
        this.id = UUID.randomUUID().toString();
    }
}
