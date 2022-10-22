package com.example.demo.batch;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Write data to db
 */
@Component
@Log4j2
public class Writer implements ItemWriter<Transaction> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminRepository adminRepository;
    private HashMap<Integer, String> hashMap = new HashMap<Integer, String>();

    @Override
    public void write(List<? extends Transaction> list) throws Exception {
        log.info(String.format("Transaction Saved: %s", list));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("1234");
        Admin admin = new Admin("1", "admin", password);
        adminRepository.save(admin);

        int index = 0;
        for(Transaction transaction: list){
            if(!hashMap.containsValue(transaction.getCustomerID())){
                hashMap.put(index, transaction.getCustomerID());
                Customer customerEntity = new Customer(UUID.randomUUID().toString(), transaction.getCustomerID());
                customerRepository.save(customerEntity);
                index ++;
            }
        }

        transactionRepository.saveAll(list);
    }
}
