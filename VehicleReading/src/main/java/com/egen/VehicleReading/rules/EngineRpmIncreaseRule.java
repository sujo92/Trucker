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


@Rule(name = "weather rule", description = "if it rains then take an umbrella")
public class EngineRpmIncreaseRule {

    VehicleReading vehicleReading;
    @Autowired
    VehicleAlertSns vehicleAlertSns;
    @Autowired
    ObjectMapper objectMapper;

    public EngineRpmIncreaseRule(VehicleReading vehicleReading){
        this.vehicleReading=vehicleReading;
    }

    @Condition
    public boolean calculateEnginRpm(@Fact("RedlineRpm") int RedlineRpm) {
        return vehicleReading.getEngineRpm() > RedlineRpm;
    }

    @Action
    public void engineRpmAlert() throws JsonProcessingException {
//        System.out.println("high: engineRPM above limit");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("High");
            alert.setAlertdesc("engineRPM above limit");
            alert.setVin(vehicleReading.getVin());
        String StringAlert = objectMapper.writeValueAsString(alert);
        vehicleAlertSns.send("engineRPM alert",StringAlert);
    }

}
