package com.egen.VehicleAlert;

import com.egen.VehicleAlert.aws.VehicleAlertsListnerSqs;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class VehicleAlertApplication {

	public static void main(String[] args) throws JsonProcessingException {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(VehicleAlertApplication.class, args);
		VehicleAlertsListnerSqs vehicleAlertsListnerSqs = applicationContext.getBean(VehicleAlertsListnerSqs.class);
		vehicleAlertsListnerSqs.startListeningToMessages();
	}

}
