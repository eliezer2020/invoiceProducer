package com.gerson.invoicestreamapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerson.invoicestreamapp.model.DeliveryAddress;
import com.gerson.invoicestreamapp.model.Invoice;
import com.gerson.invoicestreamapp.model.LineItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class InvoiceGenerator {

    @Autowired
    AddressGenerator addressGenerator;

    @Autowired
    ProductGenerator productGenerator;

    private final Random invoiceIndex;
    private final Random invoiceNumber;
    private final Random numberOfItems;
    private Invoice[] invoices;

    private final Logger logger = LoggerFactory.getLogger(InvoiceGenerator.class.getName());

    public InvoiceGenerator () {
        String filepath="src/main/resources/data/Invoice.json";
        invoiceIndex= new Random();
        invoiceNumber = new Random();
        numberOfItems = new Random();
        ObjectMapper mapper = new ObjectMapper();

        try {
            invoices= mapper.readValue(new File(filepath),Invoice[].class);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private int getIndex(){
        return invoiceIndex.nextInt(100);
    }

    private int getNewInvoiceNumber(){
        return invoiceNumber.nextInt(999999) +999;
    }

    private int getNumberOfItems(){
        return numberOfItems.nextInt(4)+1;
    }

    public Invoice getNextInvoice() {
        Invoice invoice = invoices[getIndex()];
        invoice.setInvoiceNumber(Integer.toString(getNewInvoiceNumber()));
        invoice.setCreatedTime(System.currentTimeMillis());
        if ("HOME_DELIVERY".equalsIgnoreCase(invoice.getDeliveryType())) {
            DeliveryAddress deliveryAddress = addressGenerator.getNext();
            invoice.setDeliveryAddress(deliveryAddress);
        }
        int itemCount = getNumberOfItems();
        double totalAmount = 0.0;
        List<LineItem> items = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            LineItem item = productGenerator.getNext();
            totalAmount = totalAmount + item.getTotalValue();
            items.add(item);
        }

        invoice.setNumberOfItems(itemCount);
        invoice.setInvoiceLineItems(items);
        invoice.setTotalAmount(totalAmount);
        invoice.setTaxableAmount(totalAmount);
        invoice.setCGST(totalAmount * 0.025);
        invoice.setSGST(totalAmount*0.025);
        invoice.setCESS(totalAmount*0.000125);
        logger.info(invoice.toString());
        return invoice;



    }

}
