package com.purnima.jain.kafka.config;

import java.util.HashMap;
import java.util.Map;

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

import com.purnima.jain.kafka.rest.dto.UserRequestDto;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	
	@Value("${spring.kafka.bootstrap-servers:localhost:9092}")
	private String bootstrapServers;
	
	@Value("${spring.kafka.consumer.group-id:my_app_group}")
	private String groupId;

	@Bean
	  public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		return props;
	}
	
	@Bean
	  public ConsumerFactory<String, UserRequestDto> consumerFactory() {
	    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(UserRequestDto.class));
	  }

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserRequestDto> kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, UserRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}