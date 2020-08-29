package com.egen.VehicleReading.service.Impl;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.Vehicle;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.AlertRepository;
import com.egen.VehicleReading.repo.VehicleRepository;
import com.egen.VehicleReading.service.VehicleReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;

@Service
public class DefaultVehicleReading implements VehicleReadingService {
    VehicleRepository vehicleRepository;
    AlertRepository alertRepository;
    RestTemplate restTemplate;

    @Autowired
    public DefaultVehicleReading(VehicleRepository vehicleRepository, RestTemplate restTemplate,AlertRepository alertRepository){
        this.vehicleRepository = vehicleRepository;
        this.restTemplate = restTemplate;
        this.alertRepository = alertRepository;
    }


    @Override
    public boolean saveReading(VehicleReading vehicleReading) {
        System.out.println(vehicleReading);
        vehicleRepository.save(vehicleReading);
        String vin = vehicleReading.getVin();
        Vehicle v = restTemplate.getForObject("http://localhost:8001/api/vehicle/"+vin, Vehicle.class, boolean.class);

        if(vehicleReading.getEngineRpm() > v.getRedlineRpm()){
            System.out.println("high: engineRPM above limit");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("High");
            alert.setAlertdesc("engineRPM above limit");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        if(vehicleReading.getFuelVolume() < v.getMaxFuelVolume()*0.10){
            System.out.println("medium: fuel volume below 10%");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("fuel volume below 10%");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        if(vehicleReading.getTires().getFrontLeft()<32 || vehicleReading.getTires().getFrontRight()<32 ||
                vehicleReading.getTires().getRearLeft()<32 || vehicleReading.getTires().getRearRight()<32
        ){
            System.out.println("medium: tire pressure low");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("tire pressure low");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        if(vehicleReading.getTires().getFrontRight() > 36 || vehicleReading.getTires().getFrontRight() > 36 ||
                vehicleReading.getTires().getRearLeft() > 36 || vehicleReading.getTires().getRearRight() > 36
        ){
            System.out.println("medium: tire pressure high");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Medium");
            alert.setAlertdesc("fuel tire pressure high");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        if(vehicleReading.isCheckEngineLightOn()){
            System.out.println("Low: engine light on");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Low");
            alert.setAlertdesc("engine light on");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        if(vehicleReading.isEngineCoolantLow()){
            System.out.println("Low: engine coolent low");
            Alert alert = new Alert();
            alert.setAlertTime(vehicleReading.getTimestamp());
            alert.setPriority("Low");
            alert.setAlertdesc("engine coolent low");
            alert.setVin(vehicleReading.getVin());
            alertRepository.save(alert);
        }

        return true;
    }

    public List<VehicleReading> getvehicleLocation(String vin){
        List<VehicleReading> list =  vehicleRepository.findAllbyVin(vin);
        return list;
    }

    @Override
    public List<Alert> getHistoricalAlertByVin(String vin) {
        return  alertRepository.findAllAlertsbyVin(vin);
    }

    @Override
    public List<Alert> getAllHighAlerts() {
        List<Alert> alerts = alertRepository.findAllHighAlerts();
        alerts.sort( Comparator.comparing(Alert::getAlertTime));
        return alerts;
    }
}
