package com.egen.VehicleAlert.repository;

import com.egen.VehicleAlert.model.VehicleAlert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleAlertRepository extends CrudRepository<VehicleAlert, String> {

    @Query("SELECT a FROM VehicleAlert a WHERE a.vin = :vin")
    public List<VehicleAlert> findAllAlertsbyVin(@Param("vin") String vin);

    @Query(value = "SELECT * FROM Trucker.VehicleAlert WHERE priority= 'High' AND `alert_time` > SUBDATE( CURRENT_TIMESTAMP , INTERVAL 2 HOUR)", nativeQuery = true)
    public List<VehicleAlert> findAllHighAlerts();

}
