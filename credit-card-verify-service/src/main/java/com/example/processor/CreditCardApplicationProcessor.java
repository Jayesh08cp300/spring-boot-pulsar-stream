package com.example.processor;

import com.example.event.NewCreditCardEvent;
import com.example.service.CreditCardVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
import org.apache.pulsar.common.schema.SchemaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreditCardApplicationProcessor {
	@Autowired
	private CreditCardVerificationService creditCardVerificationService;

	@Value("${spring.pulsar.producer.topic-name2}")
	private String topicName2;

	@Autowired
	private PulsarTemplate<Object> template;

	@PulsarListener(topics = "${spring.pulsar.producer.topic-name1}", subscriptionName = "credit-card", schemaType = SchemaType.JSON, subscriptionType = SubscriptionType.Shared)
	public void consumeRawEvent(NewCreditCardEvent newCreditCardEvent) throws PulsarClientException {
		var verifyCreditCardEvent = creditCardVerificationService.verifyCreditCardApplication(newCreditCardEvent);

		log.info("**** Publishing credit card applications verification status : {} **** ",
				verifyCreditCardEvent.getCreditCardVerificationStatus()
						.size());

		if (!verifyCreditCardEvent.getCreditCardVerificationStatus()
				.isEmpty()) {
			template.send(topicName2, verifyCreditCardEvent);
		}
	}

}