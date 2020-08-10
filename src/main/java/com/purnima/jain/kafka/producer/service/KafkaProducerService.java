package com.purnima.jain.kafka.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.purnima.jain.kafka.rest.dto.UserRequestDto;

@Service
public class KafkaProducerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

	private KafkaTemplate<String, UserRequestDto> kafkaTemplate;
	
	@Value("#{kafkaConfig.topicName()}")
	private String topicName;
	
	@Autowired
	KafkaProducerService(KafkaTemplate<String, UserRequestDto> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(UserRequestDto userRequestDto) {

		ListenableFuture<SendResult<String, UserRequestDto>> future = kafkaTemplate.send(topicName, userRequestDto);

		future.addCallback(new ListenableFutureCallback<SendResult<String, UserRequestDto>>() {

			@Override
			public void onSuccess(SendResult<String, UserRequestDto> result) {
				logger.info("Sent message=[" + userRequestDto + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.info("Unable to send message=[" + userRequestDto + "] due to : " + ex.getMessage());
			}
		});
	}

}
