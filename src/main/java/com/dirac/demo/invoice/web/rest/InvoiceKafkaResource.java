package com.dirac.demo.invoice.web.rest;

import com.dirac.demo.invoice.service.InvoiceKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/invoice-kafka")
public class InvoiceKafkaResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceKafkaResource.class);

    private InvoiceKafkaProducer kafkaProducer;

    public InvoiceKafkaResource(InvoiceKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
