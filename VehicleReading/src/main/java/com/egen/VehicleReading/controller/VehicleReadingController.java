package com.egen.VehicleReading.controller;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.service.VehicleReadingService;
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
    public boolean saveReading(@RequestBody VehicleReading vehicleReading){
        System.out.println("controller: save reading`");
        vehicleReadingService.saveReading(vehicleReading);
        return true;
    }

    @GetMapping("/geoLocation/{vin}")
    public List<VehicleReading> getvehicleLocation(@PathVariable String vin) {
        return vehicleReadingService.getvehicleLocation(vin);
    }

    @GetMapping("/alerts/{vin}")
    public List<Alert> getHistoricalAlertByVin(@PathVariable String vin){
        return vehicleReadingService.getHistoricalAlertByVin(vin);
    }

    @GetMapping("/highalerts")
    public List<Alert> getAllHighAlerts(){
        return vehicleReadingService.getAllHighAlerts();
    }
}
