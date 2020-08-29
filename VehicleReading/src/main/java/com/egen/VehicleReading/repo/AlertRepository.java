package com.egen.VehicleReading.repo;

import com.egen.VehicleReading.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, String> {

    @Query("SELECT a FROM Alert a WHERE a.vin = :vin")
    public List<Alert> findAllAlertsbyVin(@Param("vin") String vin);

    @Query(value = "SELECT * FROM trucker.alert WHERE priority= 'High' AND `alert_time` > SUBDATE( CURRENT_TIMESTAMP , INTERVAL 2 HOUR)", nativeQuery = true)
    public List<Alert> findAllHighAlerts();

}
