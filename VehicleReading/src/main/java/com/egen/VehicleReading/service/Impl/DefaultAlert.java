package com.egen.VehicleReading.service.Impl;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.repo.AlertRepository;
import com.egen.VehicleReading.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DefaultAlert  implements AlertService {
    @Autowired
    AlertRepository alertRepository;

    @Override
    public List<Alert> getHistoricalAlertByVin(String vin) {
        return  alertRepository.findAllAlertsbyVin(vin);
    }

    @Override
    public List<Alert> getAllHighAlertsInLastTwoHours() {
        List<Alert> alerts = alertRepository.findAllHighAlerts();
        alerts.sort( Comparator.comparing(Alert::getAlertTime));
        return alerts;
    }
}
