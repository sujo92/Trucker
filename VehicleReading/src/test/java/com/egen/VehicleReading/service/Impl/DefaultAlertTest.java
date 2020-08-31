package com.egen.VehicleReading.service.Impl;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.repo.AlertRepository;
import com.egen.VehicleReading.service.AlertService;
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
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class DefaultAlertTest {
    @MockBean
    AlertRepository alertRepository;

    @Autowired
    AlertService alertService;

    List<Alert> alertList= new ArrayList<>();

    @TestConfiguration
    static class AlertServiceImplTestConfig{
        @Bean
        public AlertService getAlertService() {
            return new DefaultAlert();
        }
    }

    @Before
    public void init(){
        Alert a = new Alert();
        a.setVin("1FMYU02143KB34432");
        a.setAlertdesc("fuel tire pressure high");
        a.setPriority("Medium");
        a.setAlertTime(new Timestamp(1598799534));
        alertList.add(a);

        Alert a1 = new Alert();
        a1.setVin("1FMYU02143KB34552");
        a1.setAlertdesc("fuel tire pressure high");
        a1.setPriority("High");
        a1.setAlertTime(new Timestamp(1598799534));
        alertList.add(a1);

        Mockito.when(alertRepository.findAllAlertsbyVin(a.getVin())).thenReturn(alertList.subList(0,1));
        Mockito.when(alertRepository.findAllHighAlerts()).thenReturn(alertList.subList(1,2));
    }

    @Test
    public void getHistoricalAlertByVin() {
        List<Alert> result = alertService.getHistoricalAlertByVin("1FMYU02143KB34432");
        Assert.assertEquals("Alert matched",alertList.subList(0,1),result);
    }

    @Test
    public void getAllHighAlertsInLastTwoHours() {
        List<Alert> result = alertService.getAllHighAlertsInLastTwoHours();
        Assert.assertEquals("Alert matched",alertList.subList(1,2),result);
    }
}