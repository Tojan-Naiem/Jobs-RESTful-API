package org.example.jobsrestfulapi.repository;

import org.example.jobsrestfulapi.model.Compony;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComponyRepository extends MongoRepository<Compony,String> {
}
