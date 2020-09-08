package com.egen.VehicleReading.rules;

import com.egen.VehicleReading.AWSMessaging.VehicleAlertSns;
import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.VehicleReading;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;

@Rule(name = "TankEmptyRule", description = "if fuel is below 10% of capacity then alert")
public class FuelVolumRule {

    VehicleReading vehicleReading;
    @Autowired
    VehicleAlertSns vehicleAlertSns;
    @Autowired
    ObjectMapper objectMapper;

    public FuelVolumRule(VehicleReading vehicleReading){
        this.vehicleReading=vehicleReading;
    }

    @Condition
    public boolean calculateFuelVolume(@Fact("MaxFuelVolume") double MaxFuelVolume) {
        return vehicleReading.getFuelVolume() < MaxFuelVolume;
    }

    @Action
    public void lowFuelAlert() throws JsonProcessingException {
//            System.out.println("medium: fuel volume below 10%");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("fuel volume below 10%");
            alert.setVin(vehicleReading.getVin());
        String StringAlert = objectMapper.writeValueAsString(alert);
        vehicleAlertSns.send("low fuel alert",StringAlert);
    }
}
