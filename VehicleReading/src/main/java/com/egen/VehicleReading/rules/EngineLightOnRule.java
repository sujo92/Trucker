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

@Rule(name = "EngineLightOnRule", description = "if tire engine light is on then alert")
public class EngineLightOnRule {
    VehicleReading vehicleReading;
    @Autowired
    VehicleAlertSns vehicleAlertSns;
    @Autowired
    ObjectMapper objectMapper;

    public EngineLightOnRule(VehicleReading vehicleReading){
        this.vehicleReading=vehicleReading;
    }

    @Condition
    public boolean checkEngineLight(@Fact("lightOn") boolean lightOn) {
        return vehicleReading.isCheckEngineLightOn();
    }

    @Action
    public void engineLightOnAlert() throws JsonProcessingException {
//            System.out.println("Low: engine light on");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Low");
            alert.setAlertdesc("engine light on");
            alert.setVin(vehicleReading.getVin());
        String StringAlert = objectMapper.writeValueAsString(alert);
        vehicleAlertSns.send("engine light on alert",StringAlert);
    }
}
