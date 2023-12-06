package com.springbatch.springbatch.steps;

import com.springbatch.springbatch.entities.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

public class ItemReaderStep extends FlatFileItemReader<Person> {

    public ItemReaderStep(){
        setName("readPersons");
        setResource(new ClassPathResource("persons.csv"));
        setLinesToSkip(1);
        setEncoding(StandardCharsets.UTF_8.name());
        setLineMapper(getLineMapper());
    }

    public LineMapper<Person> getLineMapper(){
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        String[] columns = new String[]{"name", "lastName", "dni"};
        int[] indexFields = new int[]{0,1,2};

        lineTokenizer.setNames(columns);
        lineTokenizer.setIncludedFields(indexFields);

        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Person.class);
        fieldSetMapper.setDistanceLimit(0);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        lineTokenizer.setDelimiter(",");

        return lineMapper;
    }
}
