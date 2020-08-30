package com.egen.VehicleReading.rules;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.AlertRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "EngineLightOnRule", description = "if tire engine light is on then alert")
public class EngineLightOnRule {
    VehicleReading vehicleReading;
    AlertRepository alertRepository;

    public EngineLightOnRule(VehicleReading vehicleReading, AlertRepository alertRepository){
        this.vehicleReading=vehicleReading;
        this.alertRepository = alertRepository;
    }

    @Condition
    public boolean checkEngineLight(@Fact("lightOn") boolean lightOn) {
        return vehicleReading.isCheckEngineLightOn();
    }

    @Action
    public void engineLightOnAlert() {
//            System.out.println("Low: engine light on");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Low");
            alert.setAlertdesc("engine light on");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
    }
}
