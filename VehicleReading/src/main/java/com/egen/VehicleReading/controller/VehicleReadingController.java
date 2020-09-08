package com.egen.VehicleReading.controller;

import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.service.VehicleReadingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin
public class VehicleReadingController {
    VehicleReadingService vehicleReadingService;

    @Autowired
    public VehicleReadingController(VehicleReadingService vehicleReadingService){
        this.vehicleReadingService = vehicleReadingService;
    }

    @PostMapping("/reading")
    @ApiOperation(value = "save reading")
    public boolean saveReading(@RequestBody VehicleReading vehicleReading) throws JsonProcessingException {
//        System.out.println("controller: save reading`");
        vehicleReadingService.saveReading(vehicleReading);
        return true;
    }

    @GetMapping("/geoLocation/{vin}")
    @ApiOperation(value = "get vehicles by vin",response= VehicleReading.class, responseContainer = "List")
    public List<VehicleReading> getvehicleLocation(@PathVariable String vin) {
        return vehicleReadingService.getvehicleLocation(vin);
    }
}
