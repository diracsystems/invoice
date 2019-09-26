package com.dirac.demo.invoice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InvoiceKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(InvoiceKafkaProducer.class);
    private static final String TOPIC = "topic_invoice";

    private KafkaTemplate<String, String> kafkaTemplate;

    public InvoiceKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        log.info("Producing message to {} : {}", TOPIC, message);
        this.kafkaTemplate.send(TOPIC, message);
    }
}
