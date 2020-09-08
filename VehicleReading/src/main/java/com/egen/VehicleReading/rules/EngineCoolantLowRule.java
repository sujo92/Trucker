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

@Rule(name = "EngineCoolantLowRule", description = "if engine coolant low indicator then alert")
public class EngineCoolantLowRule {
    VehicleReading vehicleReading;
    @Autowired
    VehicleAlertSns vehicleAlertSns;
    @Autowired
    ObjectMapper objectMapper;

    public EngineCoolantLowRule(VehicleReading vehicleReading){
        this.vehicleReading=vehicleReading;
    }

    @Condition
    public boolean checkEngineCoolant(@Fact("coolantLight") boolean coolantLight) {
        return vehicleReading.isEngineCoolantLow();
    }

    @Action
    public void lowEngineCoolantAlert() throws JsonProcessingException {
//            System.out.println("Low: engine coolent low");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Low");
            alert.setAlertdesc("engine coolent low");
            alert.setVin(vehicleReading.getVin());
        String StringAlert = objectMapper.writeValueAsString(alert);
        vehicleAlertSns.send("engine coolant low alert",StringAlert);
 //           alertRepository.save(alert);
    }
}
