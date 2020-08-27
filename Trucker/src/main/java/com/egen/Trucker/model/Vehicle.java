package com.egen.Trucker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
//    {
//        "vin": "1HGCR2F3XFA027534",
//            "make": "HONDA",
//            "model": "ACCORD",
//            "year": 2015,
//            "redlineRpm": 5500,
//            "maxFuelVolume": 15,
//            "lastServiceDate": "2017-05-25T17:31:25.268Z"
//    }

@Data
@NoArgsConstructor
public class Vehicle {
    private String vin;
    private String make;
    private String model;
    private int year;
    private int redlineRpm;
    private int maxFuelVolume;
    private Timestamp lastServiceDateVin;

}
