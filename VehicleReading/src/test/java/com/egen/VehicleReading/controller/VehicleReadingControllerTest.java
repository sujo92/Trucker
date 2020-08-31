package com.egen.VehicleReading.controller;

import com.egen.VehicleReading.model.Tire;
import com.egen.VehicleReading.model.VehicleReading;
import com.egen.VehicleReading.repo.VehicleRepository;
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
public class VehicleReadingControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    VehicleRepository vehicleRepository;


    @Before
    public void setup(){
        VehicleReading v = new VehicleReading();
        v.setVin("1HGCR2F3XFA027534");
        v.setLatitude(41.803194);
        v.setLongitude(-88.144406);
        v.setTimestamp(new Timestamp(1598799534));
        v.setFuelVolume(1.5);
        v.setSpeed(85);
        v.setCheckEngineLightOn(true);
        v.setEngineCoolantLow(true);
        v.setCruiseControlOn(true);
        v.setEngineRpm(6300);

        Tire t = new Tire();
        t.setRearLeft(29);
        t.setRearRight(34);
        t.setFrontRight(36);
        t.setFrontLeft(34);

        v.setTires(t);
        vehicleRepository.save(v);
    }

    @After
    public void cleanup(){
        vehicleRepository.deleteAll();
    }

    @Test
    public void saveReading() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        VehicleReading v2 = new VehicleReading();
        v2.setVin("1HGCR2F3XFA027534");
        v2.setLatitude(41.803194);
        v2.setLongitude(-88.144406);
        v2.setTimestamp(new Timestamp(1598799534));
        v2.setFuelVolume(1.5);
        v2.setSpeed(85);
        v2.setCheckEngineLightOn(true);
        v2.setEngineCoolantLow(true);
        v2.setCruiseControlOn(true);
        v2.setEngineRpm(6300);

        Tire t = new Tire();
        t.setRearLeft(29);
        t.setRearRight(34);
        t.setFrontRight(36);
        t.setFrontLeft(34);

        v2.setTires(t);
        vehicleRepository.save(v2);

        mvc.perform(MockMvcRequestBuilders.post("/vehicle/reading")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(v2))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)));
    }

    @Test
    public void getvehicleLocation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicle/geoLocation/1HGCR2F3XFA027534"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].latitude", Matchers.is(41.803194)));
    }
}