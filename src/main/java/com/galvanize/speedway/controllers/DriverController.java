package com.galvanize.speedway.controllers;

import com.galvanize.speedway.entities.Driver;
import com.galvanize.speedway.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    DriverRepository repository;

    @GetMapping("/")
    public List<Driver> getDrivers() {
        return repository.findAll();
    }

    @GetMapping("/{driver_id}")
    public Driver getDriver(@PathVariable Long driver_id) {
        return repository.findById(driver_id).get();
    }

    @PostMapping("/new")
    public Driver createDriver(@RequestBody Driver driver) {
        Driver newDriver = repository.save(driver);
        return newDriver;
    }
}
