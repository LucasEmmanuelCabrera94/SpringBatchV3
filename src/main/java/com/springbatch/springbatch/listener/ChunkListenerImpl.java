package com.springbatch.springbatch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ChunkListenerImpl implements ChunkListener {
    @Override
    public void beforeChunk(ChunkContext context) {
        System.out.println("Before chunk processing...");
        // Add any pre-processing logic here
    }

    @Override
    public void afterChunk(ChunkContext context) {
        System.out.println("After chunk processing...");
        if (context.getStepContext().getStepExecution().getStatus() == BatchStatus.FAILED) {
            System.out.println("FINALIZO EL STATUS COMPLETE...");
        }
            // Add any post-processing logic here
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        System.out.println("Error during chunk processing...");
        // Add error handling or cleanup logic here
    }
}
