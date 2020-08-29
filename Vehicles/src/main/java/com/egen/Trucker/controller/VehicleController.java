package com.egen.Trucker.controller;


import com.egen.Trucker.exceptions.ResourceNotFoundException;
import com.egen.Trucker.model.Vehicle;
import com.egen.Trucker.service.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VehicleController {

    VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService= vehicleService;
    }

    @PutMapping("/vehicles")
    public boolean addVehicle(@RequestBody Vehicle[] vehicles) throws JsonProcessingException {
//        System.out.println("controller: put vehicles");
//        vehicleService.addVehicleData(vehicles);
        System.out.println("string:"+vehicles);
        return true;
    }

    @GetMapping("/sortedReading")
    public List<Vehicle> getVehicleDataSorted(){
        return vehicleService.getVehicleDataSorted();
    }

    @GetMapping("/vehicle/{vin}")
    public Vehicle getVehicleByVin(@PathVariable String vin){
        Optional<Vehicle> vehicle = vehicleService.getVehicleByVin(vin);
        if(vehicle.isPresent())
            return vehicle.get();
        else{
            throw new ResourceNotFoundException("Vehicle with given vin not found");
        }
    }

}
