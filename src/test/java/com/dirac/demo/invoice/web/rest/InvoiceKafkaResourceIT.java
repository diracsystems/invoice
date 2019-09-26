package com.dirac.demo.invoice.web.rest;

import com.dirac.demo.invoice.InvoiceApp;
import com.dirac.demo.invoice.config.TestSecurityConfiguration;
import com.dirac.demo.invoice.service.InvoiceKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = {InvoiceApp.class, TestSecurityConfiguration.class})
public class InvoiceKafkaResourceIT {

    @Autowired
    private InvoiceKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        InvoiceKafkaResource kafkaResource = new InvoiceKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/invoice-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}
