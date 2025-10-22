package com.example.JobApp.repos;

import com.example.JobApp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepos extends JpaRepository<Company, Integer> {

    List<Company> findByLocation(String location);

    Company findByCompanyId(int companyId);

    @Modifying
    @Query("UPDATE Company c SET c.companyName = ?2, c.location = ?3, c.website = ?4 WHERE c.companyId = ?1")
    void updateCompany(int id, String name, String location, String website);

}
