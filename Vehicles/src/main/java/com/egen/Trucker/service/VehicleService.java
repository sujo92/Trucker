package com.egen.Trucker.service;

import com.egen.Trucker.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    boolean addVehicleData(Vehicle[] vehicle);
    List<Vehicle> getVehicleDataSorted();
    Optional<Vehicle> getVehicleByVin(String vin);
}
