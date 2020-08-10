package com.purnima.jain.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaJsonProducerConsumerApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaJsonProducerConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaJsonProducerConsumerApplication.class, args);
		logger.info("KafkaJsonProducerConsumerApplication Started........");
	}


}
