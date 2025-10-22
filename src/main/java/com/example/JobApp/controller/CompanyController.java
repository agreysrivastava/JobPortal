package com.example.JobApp.controller;

import com.example.JobApp.model.Company;
import com.example.JobApp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    public CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @GetMapping("/company/location/{location}")
    public ResponseEntity<?> getCompaniesByLocation(@PathVariable String location) {
        return ResponseEntity.ok(companyService.getCompaniesByLocation(location));
    }

    @PostMapping("/company")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.addCompany(company));
    }
}
