package com.egen.VehicleReading.rules;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.AlertRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;



@Rule(name = "weather rule", description = "if it rains then take an umbrella")
public class EngineRpmIncreaseRule {

    VehicleReading vehicleReading;
    AlertRepository alertRepository;

    public EngineRpmIncreaseRule(VehicleReading vehicleReading, AlertRepository alertRepository){
        this.vehicleReading=vehicleReading;
        this.alertRepository = alertRepository;
    }

    @Condition
    public boolean calculateEnginRpm(@Fact("RedlineRpm") int RedlineRpm) {
        return vehicleReading.getEngineRpm() > RedlineRpm;
    }

    @Action
    public void engineRpmAlert() {
//        System.out.println("high: engineRPM above limit");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("High");
            alert.setAlertdesc("engineRPM above limit");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
    }

}
