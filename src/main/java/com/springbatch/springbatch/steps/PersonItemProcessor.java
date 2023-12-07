package com.springbatch.springbatch.steps;

import com.springbatch.springbatch.entities.Person;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PersonItemProcessor implements ItemProcessor<Person,Person> {

    @Override
    public Person process(Person person) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();

        person.setCreateAt(formatter.format(dateTime));

        return person;
    }
}
