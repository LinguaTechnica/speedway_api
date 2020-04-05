package com.galvanize.speedway.controllers;

import com.galvanize.speedway.SpeedwayApplicationTests;
import com.galvanize.speedway.entities.Driver;
import com.galvanize.speedway.repositories.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsIterableContaining.hasItems;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DriverControllerTests extends SpeedwayApplicationTests {

    @Autowired
    private DriverController controller;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DriverRepository repository;

    @BeforeEach
    void setUp() {
//        LocalDate birthday = LocalDate.of(1990, 7, 4);
//        Driver driver = new Driver("Mockracer", "Mocky", "McMockerson", birthday);
//        when(repository.save(any(Driver.class))).thenReturn(driver);
    }

    @Test
    void controllerValid() {
        assertNotNull(controller);
    }

    @Test
    void getDriver() throws Exception {
        // Setup
        LocalDate birthday = LocalDate.of(1990, 7, 4);
        Driver driver = new Driver("Speedracer", "Tester", "McTesty", birthday);
        repository.save(driver);

        mvc.perform(get("/api/drivers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void createDriver() throws Exception {
        String driverJson = "{\"nickname\": \"Speed Racer\", \"firstName\": \"Tester\", \"lastName\": \"McTesty\", \"birthday\": \"1990-04-18\"}";
        MockHttpServletRequestBuilder request = post("/api/drivers/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(driverJson);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
//                .andExpect(content().json("[{}]"));
    }

    @Test
    void getAllDrivers() throws Exception {
        // Setup
        LocalDate birthday = LocalDate.of(1990, 7, 4);
        Driver driver = new Driver("Speedracer", "Tester", "McTesty", birthday);
//        driver.setId(1L);
        repository.save(driver);

        // Exercise and Assert
        mvc.perform(get("/api/drivers/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
//                .andExpect(jsonPath("$", hasItems("Speedracer")));
    }
}
