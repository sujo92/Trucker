package com.egen.VehicleAlert.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
public class VehicleAlert {
    @Id
    private String id;
    private String vin;
    private String priority;
    private Timestamp alertTime;
    private String alertdesc;

    public VehicleAlert(){
        this.id = UUID.randomUUID().toString();
    }
}
