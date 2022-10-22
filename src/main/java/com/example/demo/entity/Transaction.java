package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "description")
    private String description;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "trx_amount")
    private String trxAmount;
    @Column(name = "customer_id")
    private String customerID;
    @Column(name = "trx_date_time")
    private LocalDateTime trxDateTime;


    private String trxDate;
    private String trxTime;





}
