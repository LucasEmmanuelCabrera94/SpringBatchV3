package com.springbatch.springbatch.listener;

import com.springbatch.springbatch.entities.Person;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

public class ChunkItemWriterListenerImpl implements ItemWriteListener<Person> {
    @Override
    public void beforeWrite(Chunk<? extends Person> items) {
        System.out.println("Before beforeWriter processing...");
    }

    @Override
    public void afterWrite(Chunk<? extends Person> items) {
        System.out.println("After afterWriter processing...");
    }

    @Override
    public void onWriteError(Exception e, Chunk<? extends Person> items) {
        System.out.println("Error during onWriterError processing...");
    }
}
