package com.example.demo.batch;

import com.example.demo.entity.Transaction;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * Reads data from txt file
 */
public class Reader extends FlatFileItemReader<Transaction> {

    public Reader(Resource resource){
        setResource(resource);
        DefaultLineMapper<Transaction> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("|");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("accountNumber", "trxAmount", "description", "trxDate", "trxTime", "customerID");
        BeanWrapperFieldSetMapper <Transaction> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Transaction.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        setLinesToSkip(1);
        setLineMapper(defaultLineMapper);

//        resource
//        transactionFlatFileItemReader.setResource(new FileSystemResource("src/main/resources/dataSource.txt"));

    }


}
