package com.example.subscriber;

import com.example.event.VerifyCreditCardEvent;
import com.example.service.CreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.apache.pulsar.common.schema.SchemaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreditCardVerificationEventSubscriber {
	@Autowired
	private CreditCardService creditCardService;

	@PulsarListener(topics = "${spring.pulsar.producer.topic-name1}", subscriptionName = "credit-card", schemaType = SchemaType.JSON, subscriptionType = SubscriptionType.Shared)
	public void consumeEvent(VerifyCreditCardEvent verifyCreditCardEvent) {
		log.info("Received credit card application : {}", verifyCreditCardEvent.getCreditCardVerificationStatus()
				.size());
		creditCardService.generateCreditCardNumberAndCvv(verifyCreditCardEvent.getCreditCardVerificationStatus());
	}
}
