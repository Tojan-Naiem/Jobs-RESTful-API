package org.example.jobsrestfulapi.repository;

import org.example.jobsrestfulapi.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post,String> {
}