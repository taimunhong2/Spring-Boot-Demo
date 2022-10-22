package com.example.demo.batch;

import com.example.demo.entity.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private Writer writer;

    @Autowired
    private Processor processor;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob(){
        Step step = stepBuilderFactory.get("step-1")
                .<Transaction, Transaction> chunk(1)
                .reader(new Reader(new FileSystemResource("src/main/resources/dataSource.txt")))
                .processor(processor)
                .writer(writer)
                .build();

        Job job = jobBuilderFactory.get("process-job")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(step).build();
        return job;
    }

    @Bean
    public JobCompleteListener listener() {
        return new JobCompleteListener();
    }
}
