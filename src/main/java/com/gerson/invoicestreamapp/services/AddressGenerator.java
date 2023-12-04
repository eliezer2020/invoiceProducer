package com.gerson.invoicestreamapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerson.invoicestreamapp.model.DeliveryAddress;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Random;

@Service
public class AddressGenerator {

    private final Random random;
    private  DeliveryAddress[] addresses;

    public AddressGenerator(){
        final String filepath="src/main/resources/data/address.json";
        final ObjectMapper mapper;
        random= new Random();

        mapper = new ObjectMapper();

        try {
            addresses= mapper.readValue(new File(filepath), DeliveryAddress[].class);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int getIndex(){
        return random.nextInt(100);
    }
    public DeliveryAddress getNext(){
        return addresses[getIndex()];
    }
}
