package com.egen.Trucker.service.Impl;

import com.egen.Trucker.model.Vehicle;
import com.egen.Trucker.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
public class DefaultVehicleService implements VehicleService {
    List<Vehicle> vehicleData = new LinkedList<>();

    @Override
    public boolean addVehicleData(Vehicle[] vehicle) {
        for(Vehicle v: vehicle ) {
            vehicleData.add(v);
            System.out.println(v);
        }
        return true;
    }

    @Override
    public List<Vehicle> getVehicleDataSorted() {
        vehicleData.sort( Comparator.comparing(Vehicle::getYear));
        return vehicleData;
    }
}
