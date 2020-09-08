package com.egen.VehicleAlert.service;

import com.egen.VehicleAlert.model.VehicleAlert;
import com.egen.VehicleAlert.repository.VehicleAlertRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DefaultVehicleAlert implements VehicleAlertService {

    VehicleAlertRepository alertRepository;

    public DefaultVehicleAlert(VehicleAlertRepository alertRepository){
        this.alertRepository = alertRepository;
    }

    @Override
    public List<VehicleAlert> getHistoricalAlertByVin(String vin) {
        return  alertRepository.findAllAlertsbyVin(vin);
    }

    @Override
    public List<VehicleAlert> getAllHighAlertsInLastTwoHours() {
        List<VehicleAlert> alerts = alertRepository.findAllHighAlerts();
        alerts.sort( Comparator.comparing(VehicleAlert::getAlertTime));
        return alerts;
    }

    @Override
    public boolean saveAlert(VehicleAlert vehicleAlert) {
        System.out.println("In service"+vehicleAlert);
        alertRepository.save(vehicleAlert);
        return true;
    }
}
