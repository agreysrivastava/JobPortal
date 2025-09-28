package com.example.JobApp.repos;

import com.example.JobApp.model.JobPost;
import jakarta.annotation.Nullable;
import jakarta.persistence.Query;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

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

    public void addBulkData(List<JobPost> jobs) throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            for(JobPost job : jobs){
                session.persist(job);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            log.error("Error while initializing data", e);
            throw e;
        }
    }

    // method to save a job post object into arrayList
    public void addJob(JobPost job) throws Exception {
        addBulkData(Collections.singletonList(job));
        log.info("Job added to the repository: " + job);
    }

    public JobPost getJobById(int id) {
        JobPost jobPost = null;
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from JobPost where postId = :id", JobPost.class);
            query.setParameter("id", id);
            jobPost = (JobPost) query.getSingleResult();
        }
        return jobPost;
    }

    public void deleteJob(int id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("delete from JobPost where postId = :id");
            query.setParameter("id", id);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            if(result > 0){
                log.info("Job with id " + id + " deleted successfully");
            } else {
                log.warn("No job found with id " + id);
            }
        }catch (Exception e){
            log.error("Error while deleting job with id " + id, e);
        }
    }
}
