package com.egen.Trucker.controller;


import com.egen.Trucker.model.Vehicle;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VehicleController {
    
    @GetMapping("/vehicles")
    public String vehiclesGetMethod(){
        return "Vehicles are returned";
    }

    @PutMapping("/vehicles")
    public boolean addVehicle(@RequestBody Vehicle[] vehicle){
        for(Vehicle v: vehicle )
        System.out.println(v);
        return true;
    }
}
