package com.egen.VehicleReading.AWSMessaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class VehicleAlertSns {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Value("${vehicle.alert.topic}")
    String topic;

    @Autowired
    public VehicleAlertSns(NotificationMessagingTemplate notificationMessagingTemplate) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }

    public void send(String subject, String message) {
        this.notificationMessagingTemplate.sendNotification(topic, message, subject);
    }
}
