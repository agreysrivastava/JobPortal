package com.example.JobApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "JobPosts")
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
