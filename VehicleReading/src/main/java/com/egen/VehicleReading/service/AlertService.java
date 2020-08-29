package com.egen.VehicleReading.service;

import com.egen.VehicleReading.model.Alert;

import java.util.List;

public interface AlertService {
    List<Alert> getHistoricalAlertByVin(String vin);

    List<Alert> getAllHighAlertsInLastTwoHours();
}
