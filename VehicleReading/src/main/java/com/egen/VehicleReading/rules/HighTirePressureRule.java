package com.egen.VehicleReading.rules;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.AlertRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "HighTirePressureRule", description = "if tire pressure above limit then alert")
public class HighTirePressureRule {
    VehicleReading vehicleReading;
    AlertRepository alertRepository;

    public HighTirePressureRule(VehicleReading vehicleReading, AlertRepository alertRepository){
        this.vehicleReading=vehicleReading;
        this.alertRepository = alertRepository;
    }

    @Condition
    public boolean calculateTirePressure(@Fact("highPressureLimit") int highPressureLimit) {
        return vehicleReading.getTires().getFrontLeft()<highPressureLimit || vehicleReading.getTires().getFrontRight()<highPressureLimit ||
                vehicleReading.getTires().getRearLeft()<highPressureLimit || vehicleReading.getTires().getRearRight()<highPressureLimit;
    }

    @Action
    public void lowTirePressureAlert() {
//            System.out.println("medium: tire pressure high");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("fuel tire pressure high");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
    }
}
