package com.egen.VehicleReading.controller;

import com.egen.VehicleReading.model.Alert;
import com.egen.VehicleReading.repo.AlertRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class AlertControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AlertRepository alertRepository;

    @Before
    public void init(){
        Alert a = new Alert();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        a.setAlertTime(timestamp);
        a.setPriority("High");
        a.setAlertdesc("engineRPM above limit");
        a.setVin("1HGCR2F3XFA027534");

        alertRepository.save(a);
    }

    @After
    public void cleanup(){
        alertRepository.deleteAll();
    }

    @Test
    public void getHistoricalAlertByVin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/alerts/1HGCR2F3XFA027534"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("1HGCR2F3XFA027534")));
    }

//    @Test
//    public void getAllHighAlertsInLastTwoHours() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/alerts/high"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("1HGCR2F3XFA027534")));
//    }
}