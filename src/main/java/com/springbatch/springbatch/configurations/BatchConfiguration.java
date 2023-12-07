package com.springbatch.springbatch.configurations;

import com.springbatch.springbatch.entities.Person;
import com.springbatch.springbatch.steps.ItemReaderStep;
import com.springbatch.springbatch.steps.ItemWriterStep;
import com.springbatch.springbatch.steps.PersonItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean(name = "PersonItemReader")
    public ItemReaderStep itemReaderStep(){
        return new ItemReaderStep();
    }

    @Bean
    public PersonItemProcessor itemProcessorStep(){
        return new PersonItemProcessor();
    }

    @Bean
    public ItemWriterStep itemWriterStep(){
        return new ItemWriterStep();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(5);

        return taskExecutor;
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
                .processor(itemProcessorStep())
                .taskExecutor(taskExecutor())
                .build();
    }
}
