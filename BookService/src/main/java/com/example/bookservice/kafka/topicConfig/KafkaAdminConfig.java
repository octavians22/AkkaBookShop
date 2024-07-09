package com.example.bookservice.kafka.topicConfig;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAdminConfig {

		@Value("${spring.kafka.bootstrap-servers}")
		private String bootstrapServer;

		@Bean
		public KafkaAdmin admin() {
				Map<String, Object> configs = new HashMap<>();
				configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
				return new KafkaAdmin(configs);
		}

		@Bean
		public NewTopic topic() {
				return TopicBuilder
								.name("review")
								.partitions(3)
								.replicas(3)
								.build();
		}

		@Bean
		public NewTopic topicTest() {
				return TopicBuilder
								.name("book-reviews")
								.partitions(3)
								.replicas(3)
								.build();
		}
}
