package com.egen.VehicleReading.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
public class Alert {
    @Id
    private String id;
    private String vin;
    private String priority;
    private Timestamp alertTime;
    private String alertdesc;

    public Alert(){
        this.id = UUID.randomUUID().toString();
    }
}
