package org.example.jobsrestfulapi.repository;

import org.example.jobsrestfulapi.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,String> {
}
