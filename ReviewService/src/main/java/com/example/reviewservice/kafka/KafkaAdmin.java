package com.example.reviewservice.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAdmin {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public org.springframework.kafka.core.KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return new org.springframework.kafka.core.KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name("reviews")
                .partitions(3)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder
                .name("book-dto")
                .partitions(3)
                .replicas(3)
                .build();
    }
}
