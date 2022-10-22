package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private String customerID;
    private String accountNumber;
    private String description;
    private int pageNo;
    private int pageSize;
}
