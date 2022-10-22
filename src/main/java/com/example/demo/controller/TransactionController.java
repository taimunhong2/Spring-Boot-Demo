package com.example.demo.controller;

import com.example.demo.Filter.JwtTokenFilter;
import com.example.demo.FilterParam.TransactionFilterParam;
import com.example.demo.Specification.TransactionSpecification;
import com.example.demo.entity.Transaction;
import com.example.demo.model.TransactionRequest;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public Page<Transaction> getTransaction(@RequestBody @Valid TransactionRequest transactionRequest, HttpServletRequest request){
        TransactionFilterParam param = new TransactionFilterParam(transactionRequest.getCustomerID(), transactionRequest.getDescription(), transactionRequest.getAccountNumber());

        return transactionService.findTransactionsWithRequest(param, PageRequest.of(transactionRequest.getPageNo(), transactionRequest.getPageSize()));

    }

    @GetMapping("/transaction/all")
    public List<Transaction> getAllTransaction(){
        return transactionService.findAllTransaction();
    }
}
