package com.egen.Trucker.service.Impl;

import com.egen.Trucker.exceptions.ResourceNotFoundException;
import com.egen.Trucker.model.Vehicle;
import com.egen.Trucker.repo.VehicleRepository;
import com.egen.Trucker.service.VehicleService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class DefaultVehicleServiceTest {

    @TestConfiguration
    static class VehicleServiceImplTestConfig{
        @Bean
        public VehicleService getService() {
            return new DefaultVehicleService();
        }
    }

    @Autowired
    private VehicleService service;

    @MockBean
    private VehicleRepository repository;

    List<Vehicle> vehicles;

    @Before
    public void setup(){
        System.out.println("before");
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("FORD");
        vehicle.setModel("ESCAPE");
        vehicle.setMaxFuelVolume(15);
        vehicle.setRedlineRpm(6500);
        vehicle.setYear(2003);
        vehicle.setVin("1FMYU02143KB34432");
        vehicle.setLastServiceDate(new Timestamp(1598799534));

        vehicles =Collections.singletonList(vehicle);
        Mockito.when(repository.findAll()).thenReturn(vehicles);
        Mockito.when(repository.findById(vehicle.getVin())).thenReturn(Optional.of(vehicle));
    }

    @After
    public void cleanUp(){
        System.out.println("after");
    }

    @Test
    public void addVehicleData() {
        Vehicle v = new Vehicle();
        v.setMake("AUDI");
        v.setModel("S4");
        v.setMaxFuelVolume(15);
        v.setRedlineRpm(8500);
        v.setYear(2010);
        v.setVin("1FMYU02143KB35555");
        v.setLastServiceDate(new Timestamp(1598799534));
        boolean res = service.addVehicleData(new Vehicle[]{v});
        Mockito.when(repository.save(v)).thenReturn(v);
        Assert.assertEquals("return true",true,res);
    }


    @Test
    public void getVehicleDataSorted() {
        List<Vehicle> result =  service.getVehicleDataSorted();
        Assert.assertEquals("vehicles list should match", vehicles,result);

    }

    @Test
    public void getVehicleByVin() {
        Vehicle result = service.getVehicleByVin(vehicles.get(0).getVin());
        Assert.assertEquals("behicle should match",vehicles.get(0),result);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getVehicleByVinNotFound() {
        Vehicle result = service.getVehicleByVin("1223dasd");
    }
}