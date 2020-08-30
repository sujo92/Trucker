package com.egen.Trucker.controller;

import com.egen.Trucker.model.Vehicle;
import com.egen.Trucker.repo.VehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    VehicleRepository vehicleRepository;

    @Before
    public void setup(){
        Vehicle v = new Vehicle();
        v.setMake("AUDI");
        v.setModel("S4");
        v.setMaxFuelVolume(15);
        v.setRedlineRpm(8500);
        v.setYear(2010);
        v.setVin("1FMYU02143KB35555");
        vehicleRepository.save(v);
    }

    @After
    public void cleanup(){
        vehicleRepository.deleteAll();
    }

    @Test
    public void addVehicle() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Vehicle v2 = new Vehicle();
        v2.setMake("TOYOTA");
        v2.setModel("CAMRY");
        v2.setMaxFuelVolume(25);
        v2.setRedlineRpm(7500);
        v2.setYear(2018);
        v2.setVin("1FMYU02143KB36666");
        v2.setLastServiceDate(new Timestamp(1598799534));

        mvc.perform(MockMvcRequestBuilders.put("/api/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new Vehicle[]{v2}))
                )
                .andExpect(MockMvcResultMatchers.status()
                                                .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)));
    }

    @Test
    public void getVehicleDataSorted() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/vehicles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].make", Matchers.is("AUDI")));
    }

    @Test
    public void getVehicleByVin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/vehicle/1FMYU02143KB35555"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("S4")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.make", Matchers.is("AUDI")));
    }

    @Test
    public void getVehicleByVin404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/vehicle/1FMYU02143KB000555"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}