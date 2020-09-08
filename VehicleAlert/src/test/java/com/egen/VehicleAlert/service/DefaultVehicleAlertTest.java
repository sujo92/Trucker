package com.egen.VehicleAlert.service;

import com.egen.VehicleAlert.model.VehicleAlert;
import com.egen.VehicleAlert.repository.VehicleAlertRepository;
import com.egen.VehicleAlert.util.GetVehicleAlert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DefaultVehicleAlertTest {

    private VehicleAlertService vehicleAlertService;

    @Mock
    private VehicleAlertRepository vehicleAlertRepository;

    private List<VehicleAlert> list = new ArrayList<>();
    @Before
    public void setup(){
        vehicleAlertService = new DefaultVehicleAlert(vehicleAlertRepository);
        list.add(GetVehicleAlert.getHighAlert());
        list.add(GetVehicleAlert.getMediumAlert());

        Mockito.when(vehicleAlertRepository.findAllAlertsbyVin(GetVehicleAlert.getMediumAlert().getVin())).thenReturn(list.subList(0,1));
        Mockito.when(vehicleAlertRepository.findAllHighAlerts()).thenReturn(list.subList(1,2));
    }

    @Test
    public void getHistoricalAlertByVin() {
        List<VehicleAlert> result = vehicleAlertService.getHistoricalAlertByVin("1FMYU02143KB34432");
        Assert.assertEquals("Alert matched",list.subList(0,1),result);
    }

    @Test
    public void getAllHighAlertsInLastTwoHours() {
        List<VehicleAlert> result = vehicleAlertService.getAllHighAlertsInLastTwoHours();
        Assert.assertEquals("Alert matched",list.subList(1,2),result);
    }

    @Test
    public void saveAlert() {
        boolean b = vehicleAlertService.saveAlert(GetVehicleAlert.getHighAlert());
        Assert.assertEquals(b,true);
    }
}