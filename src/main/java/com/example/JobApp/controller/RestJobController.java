package com.example.JobApp.controller;

import com.example.JobApp.model.JobPost;
import com.example.JobApp.service.JobService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/v2")
public class RestJobController {
    RestJobController(){
        log.info("RestJobController instantiated");
    }


    @Autowired
    private JobService jobService;

    @GetMapping("/alljobs")
    public ResponseEntity<List<JobPost>> getAllJobs(){
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAllJobs());
    }

    @GetMapping("job/{id}")
    public ResponseEntity<JobPost> getJobById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getJobById(id));
    }

    @PostMapping("/job")
    public ResponseEntity<String> addJob(@RequestBody JobPost jobPost){
        try {
            jobService.addJob(jobPost);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Job created successfully");
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id){
        jobService.deleteJob(id);
        return ResponseEntity.status(HttpStatus.OK).body("Job deleted successfully");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        log.error("Exception occurred: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}
