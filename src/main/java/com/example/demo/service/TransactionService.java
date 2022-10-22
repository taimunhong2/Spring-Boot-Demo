package com.example.demo.service;

import com.example.demo.FilterParam.TransactionFilterParam;
import com.example.demo.Specification.TransactionSpecification;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> findAllTransaction(){
        return transactionRepository.findAll();
    }

    public Page<Transaction> findTransactionsWithRequest(TransactionFilterParam params, PageRequest pageRequest){
        Specification<Transaction> specification = TransactionSpecification.getFilteredTransaction(params);
        return transactionRepository.findAll(specification, pageRequest);
    }

    public List<Transaction> findAllTransactionsWithPagination(int offset, int pageSize){
        Page<Transaction> transactionPage = transactionRepository.findAll(PageRequest.of(offset, pageSize));

        if (transactionPage.toList().size()!= 0){
            return transactionPage.toList();
        }
        return null;
    }


}
