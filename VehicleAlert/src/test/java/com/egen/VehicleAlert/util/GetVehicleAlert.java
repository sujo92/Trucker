package com.egen.VehicleAlert.util;

import com.egen.VehicleAlert.model.VehicleAlert;

import java.sql.Timestamp;

public class GetVehicleAlert {

    public static VehicleAlert getMediumAlert(){
        VehicleAlert a = new VehicleAlert();
        a.setVin("1FMYU02143KB34432");
        a.setAlertdesc("fuel tire pressure high");
        a.setPriority("Medium");
        a.setAlertTime(new Timestamp(1598799534));
        return a;
    }

    public static VehicleAlert getHighAlert(){
        VehicleAlert a1 = new VehicleAlert();
        a1.setVin("1FMYU02143KB34552");
        a1.setAlertdesc("fuel tire pressure high");
        a1.setPriority("High");
        a1.setAlertTime(new Timestamp(1598799534));
        return a1;
    }

    public static VehicleAlert getCurrentVehicleAlert(){
        VehicleAlert a = new VehicleAlert();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        a.setAlertTime(timestamp);
        a.setPriority("High");
        a.setAlertdesc("engineRPM above limit");
        a.setVin("1HGCR2F3XFA027534");
        return a;
    }
}
