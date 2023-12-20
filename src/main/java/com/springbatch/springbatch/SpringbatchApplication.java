package com.springbatch.springbatch;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@Slf4j
@SpringBootApplication
public class SpringbatchApplication {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public static void main(String[] args) {
		SpringApplication.run(SpringbatchApplication.class, args);
	}

	@PostConstruct
	public void executeJob() {
		try {
			JobParameters jobParameter = new JobParametersBuilder()
					.addString("name", "chunk")
					.addLong("id", System.currentTimeMillis())
					.addDate("date", new Date())
					.toJobParameters();

			jobLauncher.run(job, jobParameter);

		} catch (Exception e) {
			log.error("Error al iniciar el proceso Batch, Error {}", e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
