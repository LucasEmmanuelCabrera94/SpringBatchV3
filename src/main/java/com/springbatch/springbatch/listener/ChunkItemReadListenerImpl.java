package com.springbatch.springbatch.listener;

import com.springbatch.springbatch.entities.Person;
import org.springframework.batch.core.ItemReadListener;

public class ChunkItemReadListenerImpl implements ItemReadListener<Person> {
    @Override
    public void beforeRead() {
        System.out.println("After afterRead processing...");

    }

    @Override
    public void afterRead(Person item) {
        System.out.println("After afterRead processing...");
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println("Error during onReadError processing...");
    }
}
