package com.purnima.jain.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.purnima.jain.kafka.producer.service.KafkaProducerService;
import com.purnima.jain.kafka.rest.dto.ResponseDto;
import com.purnima.jain.kafka.rest.dto.UserRequestDto;

@RestController
public class KafkaRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaRestController.class);
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@PostMapping(value = "kafka/postMessage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto postMessage(@RequestBody UserRequestDto userRequestDto) throws Exception {
		logger.info("Entering KafkaRestController.postMessage() with userRequestDto :: {}", userRequestDto);
		
		kafkaProducerService.sendMessage(userRequestDto);		
		
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Game Over for " + userRequestDto.toString());
		
		logger.info("Leaving KafkaRestController.postMessage() with responseDto:: {}", responseDto);	
		return responseDto;		
	}

}
