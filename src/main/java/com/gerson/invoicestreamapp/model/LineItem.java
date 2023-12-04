package com.gerson.invoicestreamapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//ignore null values
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineItem {

    @JsonProperty("ItemCode")
    private String itemCode;
    @JsonProperty("ItemDescription")
    private String itemDescription;
    @JsonProperty("ItemPrice")
    private double itemPrice;
    @JsonProperty("ItemQty")
    private int itemQty;
    @JsonProperty("TotalValue")
    private int totalValue;
}
