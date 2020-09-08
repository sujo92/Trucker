package com.egen.VehicleReading.util;

import com.egen.VehicleReading.model.Tire;
import com.egen.VehicleReading.model.VehicleReading;

import java.sql.Timestamp;

public class GetVehicleReading {
    public static VehicleReading getReading(){
        VehicleReading vr = new VehicleReading();

        vr.setVin("1HGCR2F3XFA027534");
        vr.setLatitude(41.803194);
        vr.setLongitude(-88.144406);
        vr.setTimestamp(new Timestamp(1598799534));
        vr.setFuelVolume(1.5);
        vr.setSpeed(85);
        vr.setEngineHp(240);
        vr.setCheckEngineLightOn(false);
        vr.setEngineCoolantLow(true);
        vr.setCruiseControlOn(true);
        vr.setEngineRpm(6300);

        Tire t = new Tire();
        t.setFrontLeft(34);
        t.setFrontRight(36);
        t.setRearLeft(29);
        t.setRearRight(34);
        vr.setTires(t);

        return vr;
    }
}
