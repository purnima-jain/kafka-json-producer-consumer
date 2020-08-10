package com.purnima.jain.kafka.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.purnima.jain.kafka.rest.dto.UserRequestDto;

@Service
public class KafkaConsumerService {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	@KafkaListener(topics = "#{kafkaConfig.topicName()}")
	// @SendTo("${replyTopic}")
	public void listen(@Payload UserRequestDto userRequestDto, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		logger.info("Received Message: " + userRequestDto + " from partition: " + partition);
	}

}
