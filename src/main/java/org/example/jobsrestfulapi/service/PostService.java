package org.example.joblisting.service;

import org.example.joblisting.dto.PostDTO;
import org.example.joblisting.exceptions.ResourcesNotFound;
import org.example.joblisting.model.Post;
import org.example.joblisting.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    public PostService(PostRepository postRepository){
        this.postRepository=postRepository;
    }
    public List<PostDTO> getAllPosts(){
        return this.postRepository.findAll()
                .stream()
                .map(s->new PostDTO(
                        s.getProfile(),
                        s.getDesc(),
                        s.getExp(),
                        s.getTeach()))
                .collect(Collectors.toList());
    }
    public PostDTO getPostById(String post_id){
        Post post =this.postRepository.findById(post_id)
                .orElseThrow(()->{throw new ResourcesNotFound("Post not found");
                });
        return new PostDTO(post.getProfile(),post.getDesc(),post.getExp(),post.getTeach());
    }
    public void addPost(PostDTO postDTO){
        Post post=new Post(postDTO.getProfile(),postDTO.getDesc(),postDTO.getExp(),postDTO.getTeach());
        this.postRepository.save(post);
    }
    public void updatePost(String post_id,PostDTO postDTO){
        Post post =this.postRepository.findById(post_id)
                .orElseThrow(()->{throw new ResourcesNotFound("Post not found");
                });
        post.setDesc(postDTO.getDesc());
        post.setExp(postDTO.getExp());
        post.setTeach(postDTO.getTeach());
        post.setProfile(post.getProfile());
    }

    public void deletePost(String post_id){
        Post post =this.postRepository.findById(post_id)
                .orElseThrow(()->{throw new ResourcesNotFound("Post not found");
                });
        this.postRepository.delete(post);

    }

}
