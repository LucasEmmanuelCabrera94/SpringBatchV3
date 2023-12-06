package com.springbatch.springbatch.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v2")
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @PostMapping("/postFile")
    public ResponseEntity<?> receiveFile(@RequestParam(name = "file") MultipartFile multipartFile){
        String fileName =  multipartFile.getOriginalFilename();

        try {
            Path path = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "files" + File.separator + fileName);

            Files.createDirectories(path.getParent());
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            log.info("------------------Inicio de proceso Batch ----------------------");

            JobParameters jobParameter = new JobParametersBuilder()
                    .addDate("date", new Date()) //para diferenciar los jobs
                    .addString("fileName", fileName)
                    .toJobParameters();

            jobLauncher.run(job, jobParameter);

            log.info("------------------Finalizacion de proceso Batch ----------------------");

            Map<String, String> response = new HashMap<>();
            response.put("fileName", fileName);
            response.put("status", "received");

            return ResponseEntity.ok(response);

        } catch (Exception e){
            log.error("Error al iniciar el proceso Batch, Error {}", e.getMessage());
            throw new RuntimeException();
        }
    }

    @GetMapping("/uploadFileInResource")
    public ResponseEntity<?> receiveFile(){
        try {
            JobParameters jobParameter = new JobParametersBuilder()
                    .addString("name", "chunk")
                    .addLong("id", System.currentTimeMillis())
                    .addDate("date", new Date())
                    .toJobParameters();

            jobLauncher.run(job, jobParameter);

            Map<String, String> response = new HashMap<>();
            response.put("status", "received");

            return ResponseEntity.ok(response);
        } catch (Exception e){
            log.error("Error al iniciar el proceso Batch, Error {}", e.getMessage());
            throw new RuntimeException();
        }
    }

}
