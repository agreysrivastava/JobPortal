package com.example.JobApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "JobPosts")
@NoArgsConstructor
public class JobPost {
    @Id
    private int postId;
    @Column(name = "post-profile", nullable = false)
    private String postProfile;
    @Column(name = "post-desc")
    private String postDesc;
    @Column(name = "exp")
    private int reqExperience;
    @Column(name = "tech-stack")
    private List<String> postTechStack;

    public JobPost(int postId, String postProfile, String postDesc, int reqExperience, List<String> postTechStack) {
        this.postId = postId;
        this.postProfile = postProfile;
        this.postDesc = postDesc;
        this.reqExperience = reqExperience;
        this.postTechStack = postTechStack;
    }
}
