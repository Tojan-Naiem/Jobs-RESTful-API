package org.example.jobsrestfulapi.repository;

import org.example.jobsrestfulapi.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company,String> {
}
