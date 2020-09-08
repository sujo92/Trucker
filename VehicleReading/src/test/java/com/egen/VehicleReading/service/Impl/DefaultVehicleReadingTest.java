package com.egen.VehicleReading.service.Impl;

import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.VehicleRepository;
import com.egen.VehicleReading.service.VehicleReadingService;
import com.egen.VehicleReading.util.GetVehicleReading;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DefaultVehicleReadingTest {

    private VehicleReadingService vehicleReadingService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    RestTemplate restTemplate;

    List<VehicleReading> list= new ArrayList<>();
    @Before
    public void setup() {
        vehicleReadingService = new DefaultVehicleReading(vehicleRepository, restTemplate);
        list.add(GetVehicleReading.getReading());
        Mockito.when(vehicleRepository.findAllbyVin(GetVehicleReading.getReading().getVin())).thenReturn(list);
    }

    @Test
    public void getvehicleLocation() {
        List<VehicleReading> result = vehicleReadingService.getvehicleLocation(GetVehicleReading.getReading().getVin());
        Assert.assertEquals("list matches", list, result);
    }

}