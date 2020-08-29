package com.egen.VehicleReading.service;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.VehicleReading;

import java.util.List;

public interface VehicleReadingService {


    boolean saveReading(VehicleReading vehicleReading);

    List<VehicleReading> getvehicleLocation(String id);

    List<Alert> getHistoricalAlertByVin(String vin);

    List<Alert> getAllHighAlerts();
}
