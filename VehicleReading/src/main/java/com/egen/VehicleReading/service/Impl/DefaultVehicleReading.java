package com.egen.VehicleReading.service.Impl;

import com.egen.VehicleReading.model.Vehicle;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.VehicleRepository;
import com.egen.VehicleReading.service.VehicleReadingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultVehicleReading implements VehicleReadingService {
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AlertCreator alertCreatorObject;

    @Autowired
    OldAlertsCreator oldAlertsCreator;

    public DefaultVehicleReading(VehicleRepository vehicleRepository, RestTemplate restTemplate) {
        this.vehicleRepository =vehicleRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean saveReading(VehicleReading vehicleReading) throws JsonProcessingException {
//        System.out.println(vehicleReading);
        vehicleRepository.save(vehicleReading);
        String vin = vehicleReading.getVin();
        Vehicle v = restTemplate.getForObject("http://localhost:8001/api/vehicle/"+vin, Vehicle.class, boolean.class);


//        System.out.println("Calling From rightWayToCall Thread " + Thread.currentThread().getName());
        System.out.println("alert:"+alertCreatorObject);
//        alertCreatorObject.createAlert(vehicleReading, v);
        oldAlertsCreator.createAlert(vehicleReading,v);

        return true;
    }

    public List<VehicleReading> getvehicleLocation(String vin){
        System.out.println("getvehicleLocation");
        List<VehicleReading> list =  vehicleRepository.findAllbyVin(vin);
        return list;
    }

}
