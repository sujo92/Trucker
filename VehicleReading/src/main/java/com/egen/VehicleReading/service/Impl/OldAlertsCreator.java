package com.egen.VehicleReading.service.Impl;

import com.egen.VehicleReading.AWSMessaging.VehicleAlertSns;
import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.Vehicle;
import com.egen.VehicleReading.model.VehicleReading;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OldAlertsCreator {

    @Autowired
    VehicleAlertSns vehicleAlertSns;
    @Autowired
    ObjectMapper objectMapper;

    public void createAlert(VehicleReading vehicleReading, Vehicle v) throws JsonProcessingException {
        if (vehicleReading.getEngineRpm() > v.getRedlineRpm()) {
//            System.out.println("high: engineRPM above limit");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("High");
            alert.setAlertdesc("engineRPM above limit");
            alert.setVin(vehicleReading.getVin());
            String StringAlert = objectMapper.writeValueAsString(alert);
            vehicleAlertSns.send("engineRPM alert",StringAlert);
//            restTemplate.postForObject("http://localhost:8004/alerts/addAlert", alert, boolean.class);
//            alertRepository.save(alert);
        }

        if (vehicleReading.getFuelVolume() < v.getMaxFuelVolume() * 0.10) {
//            System.out.println("medium: fuel volume below 10%");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("fuel volume below 10%");
            alert.setVin(vehicleReading.getVin());
            String StringAlert = objectMapper.writeValueAsString(alert);
            vehicleAlertSns.send("low fuel alert",StringAlert);
       //     restTemplate.postForObject("http://localhost:8004/alerts/addAlert", alert, boolean.class);
//            alertRepository.save(alert);
        }

        if (vehicleReading.getTires().getFrontLeft() < 32 || vehicleReading.getTires().getFrontRight() < 32 ||
                vehicleReading.getTires().getRearLeft() < 32 || vehicleReading.getTires().getRearRight() < 32
        ) {
//            System.out.println("medium: tire pressure low");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("tire pressure low");
            alert.setVin(vehicleReading.getVin());
            String StringAlert = objectMapper.writeValueAsString(alert);
            vehicleAlertSns.send("tire pressure low alert",StringAlert);
       //     restTemplate.postForObject("http://localhost:8004/alerts/addAlert", alert, boolean.class);
//            alertRepository.save(alert);
        }

        if (vehicleReading.getTires().getFrontRight() > 36 || vehicleReading.getTires().getFrontRight() > 36 ||
                vehicleReading.getTires().getRearLeft() > 36 || vehicleReading.getTires().getRearRight() > 36
        ) {
//            System.out.println("medium: tire pressure high");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("fuel tire pressure high");
            alert.setVin(vehicleReading.getVin());
            String StringAlert = objectMapper.writeValueAsString(alert);
            vehicleAlertSns.send("tire pressure high alert",StringAlert);
//            restTemplate.postForObject("http://localhost:8004/alerts/addAlert", alert, boolean.class);
//            alertRepository.save(alert);
        }

        if (vehicleReading.isCheckEngineLightOn()) {
//            System.out.println("Low: engine light on");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Low");
            alert.setAlertdesc("engine light on");
            alert.setVin(vehicleReading.getVin());
            String StringAlert = objectMapper.writeValueAsString(alert);
            vehicleAlertSns.send("engine light on alert",StringAlert);
//            restTemplate.postForObject("http://localhost:8004/alerts/addAlert", alert, boolean.class);
//            alertRepository.save(alert);
        }

        if (vehicleReading.isEngineCoolantLow()) {
//            System.out.println("Low: engine coolent low");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Low");
            alert.setAlertdesc("engine coolent low");
            alert.setVin(vehicleReading.getVin());
            String StringAlert = objectMapper.writeValueAsString(alert);
            vehicleAlertSns.send("engine coolant low alert",StringAlert);
//            restTemplate.postForObject("http://localhost:8004/alerts/addAlert", alert, boolean.class);
//            alertRepository.save(alert);
        }
    }
}
