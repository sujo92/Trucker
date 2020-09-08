package com.egen.VehicleReading.service;

import com.egen.VehicleReading.model.VehicleReading;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface VehicleReadingService {


    boolean saveReading(VehicleReading vehicleReading) throws JsonProcessingException;

    List<VehicleReading> getvehicleLocation(String id);

}
