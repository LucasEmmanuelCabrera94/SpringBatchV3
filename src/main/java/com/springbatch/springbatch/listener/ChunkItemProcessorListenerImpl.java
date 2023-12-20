package com.springbatch.springbatch.listener;

import com.springbatch.springbatch.entities.Person;
import org.springframework.batch.core.ItemProcessListener;

public class ChunkItemProcessorListenerImpl implements ItemProcessListener<Person,Person> {

    @Override
    public void beforeProcess(Person item) {
        System.out.println("Before beforeProcess processing...");
    }

    @Override
    public void afterProcess(Person item, Person result) {
        System.out.println("After afterProcess processing...");
    }

    @Override
    public void onProcessError(Person item, Exception e) {
        System.out.println("Error during onProcessError processing...");
    }

}