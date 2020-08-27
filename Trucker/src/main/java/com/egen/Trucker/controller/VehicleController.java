package com.egen.Trucker.controller;


import com.egen.Trucker.model.Vehicle;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VehicleController {

    @GetMapping("/vehicles")
    public String vehiclesGetMethod(){
        return "Vehicles are returned";
    }

    @PostMapping("/vehicles")
    public boolean addVehicle(@RequestBody Vehicle vehicle){
        System.out.println(vehicle);
        return true;
    }
}
