package com.egen.VehicleReading.repo;

import com.egen.VehicleReading.model.Tire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TireRepository extends CrudRepository<Tire, String>{}

