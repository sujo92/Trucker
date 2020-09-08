package com.egen.VehicleAlert.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.egen.VehicleAlert.model.SQSMessage;
import com.egen.VehicleAlert.model.VehicleAlert;
import com.egen.VehicleAlert.service.VehicleAlertService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class VehicleAlertsListnerSqs {


    @Value("${sqs.url}")
    private String sqsUrl;

    @Value("${cloud.aws.credentials.accessKey}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String awsSecretKey;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    private ObjectMapper objectMapper;

    VehicleAlertService vehicleAlertService;

    private AmazonSQS amazonSQS;

    @Autowired
    public VehicleAlertsListnerSqs(ObjectMapper objectMapper,
                                   VehicleAlertService vehicleAlertService,
                                   AmazonSQS amazonSQS,
                                   @Value("${sqs.url}") String sqlUrl,
                                   @Value("${cloud.aws.credentials.accessKey}") String awsAccessKey,
                                   @Value("${cloud.aws.credentials.secretKey}") String awsSecretKey,
                                   @Value("${cloud.aws.region.static}") String awsRegion){
        this.vehicleAlertService =vehicleAlertService;
        this.amazonSQS= amazonSQS;
        this.objectMapper = objectMapper;
        this.sqsUrl=sqlUrl;
        this.awsAccessKey=awsAccessKey;
        this.awsSecretKey=awsSecretKey;
        this.awsRegion=awsRegion;
    }

    @PostConstruct
    private void postConstructor() {
        System.out.println("SQS listening messages");
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
        );

        this.amazonSQS = AmazonSQSClientBuilder.standard().withCredentials(awsCredentialsProvider).build();
    }

    public void startListeningToMessages() throws JsonProcessingException {

        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsUrl)
                .withMaxNumberOfMessages(1)
                .withWaitTimeSeconds(3);

        while (true) {

            final List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();

            for (Message messageObject : messages) {
                String message = messageObject.getBody();
//
                SQSMessage sqsMessage  = objectMapper.readValue(message,SQSMessage.class);
                VehicleAlert vehicleAlert = objectMapper.readValue(sqsMessage.getMessage(), VehicleAlert.class);

                System.out.println("Vehicle Alert: " + vehicleAlert);
                vehicleAlertService.saveAlert(vehicleAlert);
                deleteMessage(messageObject);
            }
        }
    }

    private void deleteMessage(Message messageObject) {

        final String messageReceiptHandle = messageObject.getReceiptHandle();
        amazonSQS.deleteMessage(new DeleteMessageRequest(sqsUrl, messageReceiptHandle));

    }
}
