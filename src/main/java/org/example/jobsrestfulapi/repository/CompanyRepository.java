package org.example.jobsrestfulapi.repository;

import org.example.jobsrestfulapi.dto.CompanyDTO;
import org.example.jobsrestfulapi.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company,String> {
    public Page<Company> findByNameContainingIgnoreCase(String filterValue, Pageable pageable);
    public Page<Company>findByCityContainingIgnoreCase(String filterValue,Pageable pageable);
    public void deleteAllByNameIn(List<String> names);
}
