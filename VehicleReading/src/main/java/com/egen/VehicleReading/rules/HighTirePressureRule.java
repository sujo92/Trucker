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

@Rule(name = "HighTirePressureRule", description = "if tire pressure above limit then alert")
public class HighTirePressureRule {
    VehicleReading vehicleReading;
    @Autowired
    VehicleAlertSns vehicleAlertSns;
    @Autowired
    ObjectMapper objectMapper;

    public HighTirePressureRule(VehicleReading vehicleReading){
        this.vehicleReading=vehicleReading;
    }

    @Condition
    public boolean calculateTirePressure(@Fact("highPressureLimit") int highPressureLimit) {
        return vehicleReading.getTires().getFrontLeft()<highPressureLimit || vehicleReading.getTires().getFrontRight()<highPressureLimit ||
                vehicleReading.getTires().getRearLeft()<highPressureLimit || vehicleReading.getTires().getRearRight()<highPressureLimit;
    }

    @Action
    public void lowTirePressureAlert() throws JsonProcessingException {
//            System.out.println("medium: tire pressure high");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("fuel tire pressure high");
            alert.setVin(vehicleReading.getVin());
        String StringAlert = objectMapper.writeValueAsString(alert);
        vehicleAlertSns.send("tire pressure high alert",StringAlert);
    }
}
