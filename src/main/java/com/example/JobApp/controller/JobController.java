package com.example.JobApp.controller;

import com.example.JobApp.service.JobService;
import com.example.JobApp.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class JobController {

    @Autowired
    public JobService service;

    @GetMapping({"/", "home"})
    public String home(){
        return "home";
    }

    @GetMapping("addjob")
    public String addJob(){
        return "addjob";
    }

    @PostMapping("handleForm")
    public String handleForm(JobPost jobPost){
        try{
        service.addJob(jobPost);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("viewalljobs")
    public String viewJobs(Model model){
        List<JobPost> jobs = service.getAllJobs();
        model.addAttribute("jobPosts", jobs);
        return "viewalljobs";
    }
}
