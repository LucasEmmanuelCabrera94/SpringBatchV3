package com.springbatch.springbatch.steps;

import com.springbatch.springbatch.entities.Person;
import com.springbatch.springbatch.services.IPersonService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ItemWriterStep implements ItemWriter<Person> {
    @Autowired
    private IPersonService personService;

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        List<Person> lista = new ArrayList<>();

        chunk.forEach(person -> {
            if(person != null){
                log.info(person.toString());
                lista.add(person);
            }
        });

        personService.saveAll(lista);
    }
}
