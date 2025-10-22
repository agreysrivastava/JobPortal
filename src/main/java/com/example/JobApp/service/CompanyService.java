package com.example.JobApp.service;


import com.example.JobApp.model.Company;
import com.example.JobApp.repos.CompanyRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    public CompanyRepos companyRepos;

    @Autowired
    public CompanyService(CompanyRepos companyRepos) {
        this.companyRepos = companyRepos;
    }

    public Company addCompany(Company company) {
        return companyRepos.save(company);
    }

    public Company getCompanyById(int id) {
        return companyRepos.findByCompanyId(id);
    }

    public List<Company> getCompaniesByLocation(String location) {
        return companyRepos.findByLocation(location);
    }

    public void deleteCompany(int id) {
        companyRepos.deleteById(id);
    }

    public void updateCompany(int id, Company company) {
        companyRepos.updateCompany(id, company.getCompanyName(), company.getLocation(), company.getWebsite());
    }
}
