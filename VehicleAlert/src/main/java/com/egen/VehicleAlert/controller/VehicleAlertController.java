package com.egen.VehicleAlert.controller;

import com.egen.VehicleAlert.model.VehicleAlert;
import com.egen.VehicleAlert.service.VehicleAlertService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class VehicleAlertController {
    VehicleAlertService alertService;

    public VehicleAlertController(VehicleAlertService alertService){
        this.alertService = alertService;
    }

    @GetMapping("/{vin}")
    @ApiOperation(value = "get alerts by vin",response= VehicleAlert.class, responseContainer = "List")
    public List<VehicleAlert> getHistoricalAlertByVin(@PathVariable String vin){
        return alertService.getHistoricalAlertByVin(vin);
    }

    @GetMapping("/high")
    @ApiOperation(value = "get high alerts by vin for past 2 hours",response= VehicleAlert.class, responseContainer = "List")
    public List<VehicleAlert> getAllHighAlertsInLastTwoHours(){
        return alertService.getAllHighAlertsInLastTwoHours();
    }

    @PostMapping("/addAlert")
    public boolean addWeatherAlert(@RequestBody VehicleAlert vehicleAlert){
        System.out.println("In controller");
        alertService.saveAlert(vehicleAlert);
        return true;
    }
}
