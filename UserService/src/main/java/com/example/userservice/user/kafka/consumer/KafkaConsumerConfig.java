package com.example.userservice.user.kafka.consumer;

import com.example.userservice.user.dto.UserOrderDTO;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

		@Value("${spring.kafka.bootstrap-servers}")
		private String bootstrapServers;

		@Bean
		public ConsumerFactory<String, Object> consumerFactory() {
				Map<String, Object> props = new HashMap<>();
				props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
				props.put(ConsumerConfig.GROUP_ID_CONFIG, "book-desired-group");
				props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
				props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

				JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>(Object.class);
				jsonDeserializer.addTrustedPackages("*"); // Adjust according to your package needs

				return new DefaultKafkaConsumerFactory<>(props,
								new StringDeserializer(),
								jsonDeserializer);
		}

		@Bean
		public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
				ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
				factory.setConsumerFactory(consumerFactory());
				return factory;
		}

		@Bean
		public ConsumerFactory<String, List<UserOrderDTO>> consumerFactoryAvailableBook() {
				Map<String, Object> config = new HashMap<>();
				config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
				config.put(ConsumerConfig.GROUP_ID_CONFIG, "book-available-group");
				config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
				ObjectMapper om = new ObjectMapper();
				JavaType type = om.getTypeFactory().constructParametricType(List.class, UserOrderDTO.class);
				return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<List<UserOrderDTO>>(type, om, false));
		}
		@Bean
		public ConcurrentKafkaListenerContainerFactory<String, List<UserOrderDTO>> kafkaListenerContainerFactoryAvailableBook() {
				ConcurrentKafkaListenerContainerFactory<String, List<UserOrderDTO>> factory =
								new ConcurrentKafkaListenerContainerFactory<>();
				factory.setConsumerFactory(consumerFactoryAvailableBook());
				return factory;
		}
}
