package com.egen.VehicleReading.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "tires")
public class Tire implements Serializable {
    @Id
    @Column(name = "tire_id")
    private String id;
    private int frontLeft;
    private int frontRight;
    private int rearLeft;
    private int rearRight;

////    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tire")
//    @OneToOne
//    @JoinColumn(name="tire_id", referencedColumnName = "id")
 //   private VehicleReading vehicleReading;

    public Tire(){
        this.id = UUID.randomUUID().toString();
    }
}
