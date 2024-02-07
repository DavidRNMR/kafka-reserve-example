package com.hotel.springboot.config;

import com.google.common.collect.ImmutableMap;
import com.hotel.springboot.payload.Reservation;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, Reservation> producerFactory (){

        ImmutableMap.Builder<String, Object> immutableMapBuilder = ImmutableMap.<String,Object>builder()
                .put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers)
                .put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
                .put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
                .put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,1)
                .put(ProducerConfig.ACKS_CONFIG,"all")
                .put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,true);

        return new DefaultKafkaProducerFactory<>(immutableMapBuilder.build());
    }

    @Bean
    public KafkaTemplate<String, Reservation> kafkaTemplate (@Autowired ProducerFactory<String, Reservation> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
