package com.example.JobApp.service;

import com.example.JobApp.model.JobPost;
import com.example.JobApp.repos.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    public JobRepo jobRepo;

    public void addJob(JobPost jobPost) throws Exception{
        jobRepo.addJob(jobPost);
    }
     public List<JobPost> getAllJobs(){
        return jobRepo.getAllJobs();
     }

     public JobPost getJobById(int id){
        return jobRepo.getJobById(id);
     }

     public void deleteJob(int id){
        jobRepo.deleteJob(id);
     }

     public void updateJob(int id, JobPost jobPost) throws Exception {
        jobRepo.updateJob(id, jobPost);
     }
}
