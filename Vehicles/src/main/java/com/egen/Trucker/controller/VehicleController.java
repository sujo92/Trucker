package com.egen.Trucker.controller;


import com.egen.Trucker.model.Vehicle;
import com.egen.Trucker.service.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        vehicleService.addVehicleData(vehicles);
        return true;
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getVehicleDataSorted(){
        return vehicleService.getVehicleDataSorted();
    }

    @GetMapping("/vehicle/{vin}")
    public Vehicle getVehicleByVin(@PathVariable String vin) throws JsonProcessingException {
        return vehicleService.getVehicleByVin(vin);
    }

}
