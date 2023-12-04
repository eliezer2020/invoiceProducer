package com.gerson.invoicestreamapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Invoice {

    @JsonProperty("InvoiceNumber")
    public String invoiceNumber;
    @JsonProperty("CreatedTime")
    public String createdTime;
    @JsonProperty("StoreID")
    public String storeID;
    @JsonProperty("PosID")
    public String posID;
    @JsonProperty("CashierID")
    public String cashierID;
    @JsonProperty("CustomerType")
    public String customerType;
    @JsonProperty("CustomerCardNo")
    public String customerCardNo;
    @JsonProperty("TotalAmount")
    public double totalAmount;
    @JsonProperty("NumberOfItems")
    public int numberOfItems;
    @JsonProperty("PaymentMethod")
    public String paymentMethod;
    @JsonProperty("TaxableAmount")
    public double taxableAmount;
    @JsonProperty("CGST")
    public double cGST;
    @JsonProperty("SGST")
    public double sGST;
    @JsonProperty("CESS")
    public double cESS;
    @JsonProperty("DeliveryType")
    public String deliveryType;

    @JsonProperty("DeliveryAddress")
    public DeliveryAddress deliveryAddress;

    @JsonProperty("InvoiceLineItems")
    public List <LineItem> lineItems;



}
