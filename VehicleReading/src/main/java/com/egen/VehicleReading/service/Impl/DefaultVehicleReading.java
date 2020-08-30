package com.egen.VehicleReading.service.Impl;

import com.egen.VehicleReading.model.Vehicle;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.AlertRepository;
import com.egen.VehicleReading.repo.VehicleRepository;
import com.egen.VehicleReading.rules.*;
import com.egen.VehicleReading.service.VehicleReadingService;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultVehicleReading implements VehicleReadingService {
    VehicleRepository vehicleRepository;
    AlertRepository alertRepository;
    RestTemplate restTemplate;

    @Autowired
    Facts facts;
    @Autowired
    Rules rules;
    @Autowired
    RulesEngine rulesEngine;

    @Autowired
    public DefaultVehicleReading(VehicleRepository vehicleRepository, RestTemplate restTemplate,AlertRepository alertRepository){
        this.vehicleRepository = vehicleRepository;
        this.restTemplate = restTemplate;
        this.alertRepository = alertRepository;
    }


    @Override
    public boolean saveReading(VehicleReading vehicleReading) {
//        System.out.println(vehicleReading);
        vehicleRepository.save(vehicleReading);
        String vin = vehicleReading.getVin();
        Vehicle v = restTemplate.getForObject("http://localhost:8001/api/vehicle/"+vin, Vehicle.class, boolean.class);

        // define facts
        facts.put("RedlineRpm", v.getRedlineRpm());
        facts.put("MaxFuelVolume", v.getMaxFuelVolume()*0.10);
        facts.put("lowPressureLimit", 32);
        facts.put("highPressureLimit", 36);
        facts.put("lightOn", true);
        facts.put("coolantLight", true);

        // define rules
        rules.register(new EngineRpmIncreaseRule(vehicleReading,alertRepository));
        rules.register(new FuelVolumRule(vehicleReading,alertRepository));
        rules.register(new LowTirePressureRule(vehicleReading,alertRepository));
        rules.register(new HighTirePressureRule(vehicleReading,alertRepository));
        rules.register(new EngineLightOnRule(vehicleReading,alertRepository));
        rules.register(new EngineCoolantLowRule(vehicleReading,alertRepository));

        // fire rules on known facts
        rulesEngine.fire(rules, facts);

        return true;
    }

    public List<VehicleReading> getvehicleLocation(String vin){
        List<VehicleReading> list =  vehicleRepository.findAllbyVin(vin);
        return list;
    }

}
