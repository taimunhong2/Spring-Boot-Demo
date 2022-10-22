package com.example.demo.controller;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private Job processJob;

    @GetMapping("/admin/getAllTransaction")
    public List<Transaction> getAllTransaction (){
        return transactionService.findAllTransaction();
    }


    @GetMapping("/admin/batch-job")
    public String runJob() throws Exception{

        if(transactionRepository.count()>0){
            return "Batch job has invoked.";
        }

        JobParameters jobParameters = new JobParametersBuilder().addLong("unique", System.nanoTime()).toJobParameters();

        jobLauncher.run(processJob, jobParameters);
        return "Batch job has invoked.";
    }
}
