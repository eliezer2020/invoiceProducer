package com.gerson.invoicestreamapp.services.kafkaProducer;

import com.gerson.invoicestreamapp.InvoicestreamappApplication;
import com.gerson.invoicestreamapp.model.Invoice;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class KafkaProducerService {

    @Value("${application.configs.topic.name}")
    private String TOPIC_NAME;

    @Autowired
    private KafkaTemplate<String, Invoice> kafkaTemplate;

    public void sendMessage(Invoice invoice ){
        log.info(String.format("producing invoice no. %s ",invoice.getInvoiceNumber()));
        kafkaTemplate.send(TOPIC_NAME,invoice.getStoreID(),invoice);
    }
}
