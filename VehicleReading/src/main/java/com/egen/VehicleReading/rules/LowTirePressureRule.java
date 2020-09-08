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

@Rule(name = "LowTirePressureRule", description = "if tire pressure below limit then alert")
public class LowTirePressureRule {
    VehicleReading vehicleReading;
    @Autowired
    VehicleAlertSns vehicleAlertSns;
    @Autowired
    ObjectMapper objectMapper;

    public LowTirePressureRule(VehicleReading vehicleReading){
        this.vehicleReading=vehicleReading;
    }

    @Condition
    public boolean calculateTirePressure(@Fact("lowPressureLimit") int lowPressureLimit) {
        return vehicleReading.getTires().getFrontLeft()<lowPressureLimit || vehicleReading.getTires().getFrontRight()<lowPressureLimit ||
                vehicleReading.getTires().getRearLeft()<lowPressureLimit || vehicleReading.getTires().getRearRight()<lowPressureLimit;
    }

    @Action
    public void lowTirePressureAlert() throws JsonProcessingException {
//            System.out.println("medium: tire pressure low");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("tire pressure low");
            alert.setVin(vehicleReading.getVin());
        String StringAlert = objectMapper.writeValueAsString(alert);
        vehicleAlertSns.send("tire pressure low alert",StringAlert);
    }
}
