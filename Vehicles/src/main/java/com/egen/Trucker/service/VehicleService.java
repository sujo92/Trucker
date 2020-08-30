package com.egen.Trucker.service;

import com.egen.Trucker.model.Vehicle;

import java.util.List;

public interface VehicleService {

    boolean addVehicleData(Vehicle[] vehicle);
    List<Vehicle> getVehicleDataSorted();
    Vehicle getVehicleByVin(String vin);
}
