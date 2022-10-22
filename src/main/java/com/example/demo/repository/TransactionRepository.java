package com.example.demo.repository;


import com.example.demo.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> , JpaSpecificationExecutor<Transaction> {

    Optional<List<Transaction>> findAllByCustomerIDAndDescriptionAndAccountNumber(Optional<String> customerID, Optional<String> description, Optional<String> accountNumber);
}
