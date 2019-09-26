package com.dirac.demo.invoice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class InvoiceKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(InvoiceKafkaConsumer.class);
    private static final String TOPIC = "topic_invoice";

    @KafkaListener(topics = "topic_invoice", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
