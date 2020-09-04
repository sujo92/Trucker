package com.egen.VehicleReading.service.Impl;

import com.egen.VehicleReading.model.Vehicle;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.AlertRepository;
import com.egen.VehicleReading.rules.*;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlertCreator {

    @Autowired
    Facts facts;
    @Autowired
    Rules rules;
    @Autowired
    RulesEngine rulesEngine;
    @Autowired
    AlertRepository alertRepository;


    public void createAlert(VehicleReading vehicleReading, Vehicle v){
        System.out.println("Trigger alert in a New Thread :: "  + Thread.currentThread().getName());
        System.out.println(">>>vin being processed: "+ vehicleReading.getVin());
        // define facts
        synchronized(this) {
            facts.put("RedlineRpm", v.getRedlineRpm());
            facts.put("MaxFuelVolume", v.getMaxFuelVolume() * 0.10);
            facts.put("lowPressureLimit", 32);
            facts.put("highPressureLimit", 36);
            facts.put("lightOn", true);
            facts.put("coolantLight", true);
            System.out.println(">>fact:" + facts);
        }
        // define rules
        rules.register(new EngineRpmIncreaseRule(vehicleReading,alertRepository));
        rules.register(new FuelVolumRule(vehicleReading,alertRepository));
        rules.register(new LowTirePressureRule(vehicleReading,alertRepository));
        rules.register(new HighTirePressureRule(vehicleReading,alertRepository));
        rules.register(new EngineLightOnRule(vehicleReading,alertRepository));
        rules.register(new EngineCoolantLowRule(vehicleReading,alertRepository));

        // fire rules on known facts
        rulesEngine.fire(rules, facts);
    }
}
