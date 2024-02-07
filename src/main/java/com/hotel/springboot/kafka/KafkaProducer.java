package com.hotel.springboot.kafka;

import com.hotel.springboot.payload.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, Reservation> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, Reservation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Reservation reservation) {

        CompletableFuture<SendResult<String,Reservation>> future =
                kafkaTemplate.send(topicName,reservation);

        future.whenComplete((result,ex)->{

            if(ex==null){
                LOGGER.info(String.format("Message sent -> %s", reservation.toString()));
            }
            else{
                LOGGER.error(String.format("Error sending record: -> %s", reservation.toString()));
            }
        });
    }
 }
