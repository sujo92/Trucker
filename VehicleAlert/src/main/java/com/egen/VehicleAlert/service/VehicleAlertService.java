package com.egen.VehicleAlert.service;

import com.egen.VehicleAlert.model.VehicleAlert;

import java.util.List;

public interface VehicleAlertService {
    List<VehicleAlert> getHistoricalAlertByVin(String vin);

    List<VehicleAlert> getAllHighAlertsInLastTwoHours();

    boolean saveAlert(VehicleAlert vehicleAlert);
}
