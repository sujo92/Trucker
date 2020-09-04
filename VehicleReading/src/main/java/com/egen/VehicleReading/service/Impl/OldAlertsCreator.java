package com.egen.VehicleReading.service.Impl;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.Vehicle;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.AlertRepository;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OldAlertsCreator {
    @Autowired
    Facts facts;
    @Autowired
    Rules rules;
    @Autowired
    RulesEngine rulesEngine;
    @Autowired
    AlertRepository alertRepository;

    public void createAlert(VehicleReading vehicleReading, Vehicle v) {
        if (vehicleReading.getEngineRpm() > v.getRedlineRpm()) {
            System.out.println("high: engineRPM above limit");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("High");
            alert.setAlertdesc("engineRPM above limit");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        if (vehicleReading.getFuelVolume() < v.getMaxFuelVolume() * 0.10) {
            System.out.println("medium: fuel volume below 10%");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("fuel volume below 10%");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        if (vehicleReading.getTires().getFrontLeft() < 32 || vehicleReading.getTires().getFrontRight() < 32 ||
                vehicleReading.getTires().getRearLeft() < 32 || vehicleReading.getTires().getRearRight() < 32
        ) {
            System.out.println("medium: tire pressure low");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("tire pressure low");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        if (vehicleReading.getTires().getFrontRight() > 36 || vehicleReading.getTires().getFrontRight() > 36 ||
                vehicleReading.getTires().getRearLeft() > 36 || vehicleReading.getTires().getRearRight() > 36
        ) {
            System.out.println("medium: tire pressure high");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("fuel tire pressure high");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        if (vehicleReading.isCheckEngineLightOn()) {
            System.out.println("Low: engine light on");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Low");
            alert.setAlertdesc("engine light on");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        if (vehicleReading.isEngineCoolantLow()) {
            System.out.println("Low: engine coolent low");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Low");
            alert.setAlertdesc("engine coolent low");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }
    }
}
