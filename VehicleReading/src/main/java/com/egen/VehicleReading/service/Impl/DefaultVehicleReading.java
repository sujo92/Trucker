package com.egen.VehicleReading.service.Impl;

import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.VehicleRepository;
import com.egen.VehicleReading.service.VehicleReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultVehicleReading implements VehicleReadingService {
    VehicleRepository vehicleRepository;

    @Autowired
    public DefaultVehicleReading(VehicleRepository vehicleRepository){
        this.vehicleRepository=vehicleRepository;
    }


    @Override
    public boolean saveReading(VehicleReading vehicleReading) {
//        System.out.println(vehicleReading);
        vehicleRepository.save(vehicleReading);
        return true;
    }

    public List<VehicleReading> getvehicleLocation(String vin){
        List<VehicleReading> list =  vehicleRepository.findAllbyVin(vin);
        return list;
    }
}
