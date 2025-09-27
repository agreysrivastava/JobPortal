package com.example.JobApp;

import com.example.JobApp.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    public JobRepo jobRepo;

    public void addJob(JobPost jobPost){
        jobRepo.addJob(jobPost);
    }
     public List<JobPost> getAllJobs(){
        return jobRepo.getAllJobs();
     }
}
