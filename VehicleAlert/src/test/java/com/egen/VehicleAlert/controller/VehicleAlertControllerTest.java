package com.egen.VehicleAlert.controller;

import com.egen.VehicleAlert.model.VehicleAlert;
import com.egen.VehicleAlert.repository.VehicleAlertRepository;
import com.egen.VehicleAlert.util.GetVehicleAlert;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class VehicleAlertControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private VehicleAlertRepository alertRepository;

    @Before
    public void init(){
        VehicleAlert a = GetVehicleAlert.getCurrentVehicleAlert();

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

//    @Test
//    public void addWeatherAlert() {
//    }
}