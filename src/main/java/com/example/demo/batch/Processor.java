package com.example.demo.batch;

import com.example.demo.entity.Transaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
@Component
public class Processor implements ItemProcessor<Transaction,Transaction> {
    @Override
    public Transaction process(Transaction transaction) throws Exception {

        transaction.setId(UUID.randomUUID().toString());

        LocalDate datePart = LocalDate.parse(transaction.getTrxDate());
        LocalTime timePart = LocalTime.parse(transaction.getTrxTime());
        LocalDateTime dateTime = LocalDateTime.of(datePart, timePart);
        transaction.setTrxDateTime(dateTime);
        return transaction;
    }
}
