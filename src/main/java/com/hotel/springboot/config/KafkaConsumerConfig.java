package com.hotel.springboot.config;

import com.google.common.collect.ImmutableMap;
import com.hotel.springboot.payload.Reservation;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    public ImmutableMap<String, Object> consumerFactory (String groupId){

        ImmutableMap.Builder<String, Object> immutableMapBuilder = ImmutableMap.<String,Object>builder()
                .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers)
                .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class)
                .put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
                .put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
                .put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, false)
                .put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 500)
                .put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 500)
                .put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1)
                .put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return immutableMapBuilder.build();
    }

    @Bean
    public ConsumerFactory<String, Reservation> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(this.consumerFactory(this.groupId));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Reservation> kafkaListenerContainerFactory(
            @Autowired ConsumerFactory<String, Reservation> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Reservation> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}

