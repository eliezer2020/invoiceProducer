package com.gerson.invoicestreamapp;

import com.gerson.invoicestreamapp.services.InvoiceGenerator;
import com.gerson.invoicestreamapp.services.ProductGenerator;
import com.gerson.invoicestreamapp.services.kafkaProducer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InvoicestreamappApplication implements ApplicationRunner {
@Autowired
InvoiceGenerator invoiceGenerator;
@Autowired
	KafkaProducerService kafkaProducerService;

@Value("${application.configs.invoice.count")
private int INVOICE_COUNT;



	public static void main(String[] args) {
		SpringApplication.run(InvoicestreamappApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

		for (int i=0;i<INVOICE_COUNT;i++){
			kafkaProducerService.sendMessage(invoiceGenerator.getNextInvoice());
			Thread.sleep(1000);
		}

	}
}
