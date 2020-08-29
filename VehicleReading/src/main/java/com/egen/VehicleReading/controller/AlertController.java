package com.egen.VehicleReading.controller;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.service.AlertService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {
    AlertService alertService;

    public AlertController(AlertService alertService){
        this.alertService = alertService;
    }

    @GetMapping("/{vin}")
    @ApiOperation(value = "get alerts by vin",response= Alert.class, responseContainer = "List")
    public List<Alert> getHistoricalAlertByVin(@PathVariable String vin){
        return alertService.getHistoricalAlertByVin(vin);
    }

    @GetMapping("/high")
    @ApiOperation(value = "get high alerts by vin for past 2 hours",response= Alert.class, responseContainer = "List")
    public List<Alert> getAllHighAlertsInLastTwoHours(){
        return alertService.getAllHighAlertsInLastTwoHours();
    }
}
