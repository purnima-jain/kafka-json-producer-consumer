package com.purnima.jain.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
	
	@Value("${spring.kafka.topic-name:my_topic}")
	private String topicName;
	
	@Bean
	public String topicName() {
	    return topicName;
	}

}
