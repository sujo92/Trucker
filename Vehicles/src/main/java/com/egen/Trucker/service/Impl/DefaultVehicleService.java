package com.egen.Trucker.service.Impl;

import com.egen.Trucker.exceptions.ResourceNotFoundException;
import com.egen.Trucker.model.Vehicle;
import com.egen.Trucker.repo.VehicleRepository;
import com.egen.Trucker.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultVehicleService implements VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

//    @Autowired
//    public DefaultVehicleService(VehicleRepository vehicleRepository){
//        this.vehicleRepository = vehicleRepository;
//    }

    @Override
    public boolean addVehicleData(Vehicle[] vehicle) {
        System.out.println("service: put vehicles");
        for(Vehicle v: vehicle ) {
            vehicleRepository.save(v);
            System.out.println(v);
        }
        return true;
    }

    @Override
    public List<Vehicle> getVehicleDataSorted() {
        List<Vehicle> vehicles = (List<Vehicle>) vehicleRepository.findAll();
        vehicles.sort( Comparator.comparing(Vehicle::getYear));
        return vehicles;
    }

    @Override
    public Vehicle getVehicleByVin(String vin) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vin);
        if(!vehicle.isPresent())
            throw new ResourceNotFoundException("Vehicle with vin"+vin+"doesnt exist");
        return vehicle.get();
    }


}
