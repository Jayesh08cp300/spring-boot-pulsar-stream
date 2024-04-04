package com.example.publisher;

import com.example.service.EventPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreditCardEventPublisher {

	@Value("${spring.pulsar.producer.topic-name1}")
	private String topicName1;

	@Autowired
	private PulsarTemplate<Object> template;

	@Autowired
	private EventPublisherService eventPublisherService;

	@Scheduled(fixedRate = 2000)
	public void publishEvent() throws PulsarClientException {
		var newCreditCardEvent = eventPublisherService.publishEvent();
		if (!newCreditCardEvent.isEmpty()) {
			template.send(topicName1, newCreditCardEvent.get());
		}
	}

}