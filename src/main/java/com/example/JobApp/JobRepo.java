package com.example.JobApp;

import com.example.JobApp.model.JobPost;
import jakarta.annotation.Nullable;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@Log4j2
public class JobRepo {

    private final SessionFactory sessionFactory;

    @Autowired
        public JobRepo(@Nullable SessionFactory sf){
        sessionFactory = sf;
    }

    public List<JobPost> getAllJobs() {
        log.info("Getting all jobs from the repository");
        List<JobPost> jobPosts;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            jobPosts = session.createQuery("from JobPost", JobPost.class).list();
            session.getTransaction().commit();
        }catch (Exception e){
            log.error("Error while fetching jobs from database, returning mock data", e);
            jobPosts = Collections.emptyList();
        }
        return jobPosts;
    }

    public void addBulkData(List<JobPost> jobs){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            for(JobPost job : jobs){
                session.persist(job);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            log.error("Error while initializing data", e);
        }
    }

    // method to save a job post object into arrayList
    public void addJob(JobPost job) {
        addBulkData(Collections.singletonList(job));
        log.info("Job added to the repository: " + job);
    }
}
