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
        System.err.println("Error durante el procesamiento del chunk: " + context.getAttribute("sb_rollback_exception").toString());
    }
}
