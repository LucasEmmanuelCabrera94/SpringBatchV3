package com.springbatch.springbatch.configurations;

import com.springbatch.springbatch.entities.Person;
import com.springbatch.springbatch.steps.ItemDescompressStep;
import com.springbatch.springbatch.steps.ItemProcessorStep;
import com.springbatch.springbatch.steps.ItemReaderStep;
import com.springbatch.springbatch.steps.ItemWriterStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Bean
    public ItemDescompressStep itemDescompressStep(){
        return new ItemDescompressStep();
    }

    @Bean
    @JobScope
    public ItemReaderStep itemReaderStep(){
        return new ItemReaderStep();
    }

/*    @Bean
    @JobScope
    public ItemProcessorStep itemProcessorStep(){
        return new ItemProcessorStep();
    }*/

    @Bean
    @JobScope
    public ItemWriterStep itemWriterStep(){
        return new ItemWriterStep();
    }

    @Bean(name = "batchJob-v1")
    public Job job(JobRepository jobRepository, @Qualifier("firstStep") Step firstStep) {
        return new JobBuilder("batchJob-v1", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstStep)
                .build();
    }

    @Bean
    protected Step firstStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("readFile", jobRepository)
                .<Person, Person> chunk(2, transactionManager)
                .reader(itemReaderStep())
                .writer(itemWriterStep())
                .build();
    }
}
