package com.example.demo.FilterParam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionFilterParam {
    private String customerID;
    private String description;
    private String accountNumber;
}
