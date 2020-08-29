package com.egen.VehicleReading.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name="vehicleReadings")
public class VehicleReading implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    private String vin;
    private double latitude;
    private double longitude;
    private Timestamp timestamp;
    private double fuelVolume;
    private int speed;
    private int  engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private int engineRpm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id", referencedColumnName = "tire_id")
    private Tire tires;

    public VehicleReading(){
        this.id = UUID.randomUUID().toString();
    }
}
