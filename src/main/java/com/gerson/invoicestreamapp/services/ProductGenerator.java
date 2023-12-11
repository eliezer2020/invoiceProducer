package com.gerson.invoicestreamapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerson.invoicestreamapp.model.DeliveryAddress;
import com.gerson.invoicestreamapp.model.LineItem;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductGenerator {

    private final Random random;
    private final Random qty;
    private  LineItem[] lineItems;

    public ProductGenerator(){
        final String filepath="src/main/resources/data/products.json";
        final ObjectMapper mapper;
        random= new Random();
        qty = new Random();


        mapper = new ObjectMapper();

        try {
            lineItems= mapper.readValue(new File(filepath), LineItem[].class);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int getIndex(){
        return random.nextInt(100);
    }

    private  int getQty(){
        return qty.nextInt(2) +1;
    }
    public LineItem getNext(){
        LineItem lineItem= lineItems[getIndex()];
        lineItem.setItemQty(getQty());
        lineItem.setTotalValue( (lineItem.getItemPrice()* lineItem.getItemQty()));
        return lineItem;
    }
}
