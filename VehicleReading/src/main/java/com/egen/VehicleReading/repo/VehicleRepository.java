package com.egen.VehicleReading.repo;

import com.egen.VehicleReading.model.VehicleReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleReading, String> {
    @Query("SELECT vr FROM VehicleReading vr WHERE vr.vin = :vin")
    public List<VehicleReading> findAllbyVin(@Param("vin") String vin);
}
