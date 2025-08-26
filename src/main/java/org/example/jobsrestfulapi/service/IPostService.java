package org.example.jobsrestfulapi.service;

import org.example.jobsrestfulapi.dto.PostDTO;
import org.example.jobsrestfulapi.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService {
    public Page<PostDTO> getAllPosts(Pageable pageable);
    public PostDTO getPostById(String post_id);
    public Post addPost(PostDTO postDTO);
    public void updatePost(String post_id,PostDTO postDTO);
    public Page<PostDTO> searchByText(String text,Pageable pageable);
    public void deletePost(String post_id);
}
