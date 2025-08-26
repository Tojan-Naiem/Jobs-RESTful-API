package org.example.jobsrestfulapi.repository;

import org.example.jobsrestfulapi.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post,String> {
    Page<Post>findByCompanyID(Pageable pageable,String companyID);
}