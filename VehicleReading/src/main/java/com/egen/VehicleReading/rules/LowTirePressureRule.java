package com.egen.VehicleReading.rules;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.AlertRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "LowTirePressureRule", description = "if tire pressure below limit then alert")
public class LowTirePressureRule {
    VehicleReading vehicleReading;
    AlertRepository alertRepository;

    public LowTirePressureRule(VehicleReading vehicleReading, AlertRepository alertRepository){
        this.vehicleReading=vehicleReading;
        this.alertRepository = alertRepository;
    }

    @Condition
    public boolean calculateTirePressure(@Fact("lowPressureLimit") int lowPressureLimit) {
        return vehicleReading.getTires().getFrontLeft()<lowPressureLimit || vehicleReading.getTires().getFrontRight()<lowPressureLimit ||
                vehicleReading.getTires().getRearLeft()<lowPressureLimit || vehicleReading.getTires().getRearRight()<lowPressureLimit;
    }

    @Action
    public void highTirePressureAlert() {
//            System.out.println("medium: tire pressure low");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("tire pressure low");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
    }
}
