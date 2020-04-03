package com.galvanize.speedway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.galvanize.speedway.entities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
