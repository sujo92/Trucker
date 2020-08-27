package com.egen.Trucker.controller;


import com.egen.Trucker.model.Vehicle;
import com.egen.Trucker.service.VehicleService;
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

    @GetMapping("/vehicles")
    public String vehiclesGetMethod(){
        return "Vehicles are returned";
    }

    @PutMapping("/vehicles")
    public boolean addVehicle(@RequestBody Vehicle[] vehicle){
        vehicleService.addVehicleData(vehicle);
        return true;
    }

    @GetMapping("/sortedReading")
    public List<Vehicle> getVehicleDataSorted(){
        return vehicleService.getVehicleDataSorted();
    }
}
