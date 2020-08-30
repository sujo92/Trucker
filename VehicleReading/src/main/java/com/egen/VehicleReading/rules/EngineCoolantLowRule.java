package com.egen.VehicleReading.rules;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.AlertRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "EngineCoolantLowRule", description = "if engine coolant low indicator then alert")
public class EngineCoolantLowRule {
    VehicleReading vehicleReading;
    AlertRepository alertRepository;

    public EngineCoolantLowRule(VehicleReading vehicleReading, AlertRepository alertRepository){
        this.vehicleReading=vehicleReading;
        this.alertRepository = alertRepository;
    }

    @Condition
    public boolean checkEngineCoolant(@Fact("coolantLight") boolean coolantLight) {
        return vehicleReading.isEngineCoolantLow();
    }

    @Action
    public void lowEngineCoolantAlert() {
//            System.out.println("Low: engine coolent low");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Low");
            alert.setAlertdesc("engine coolent low");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
    }
}
