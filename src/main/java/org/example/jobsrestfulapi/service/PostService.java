package org.example.jobsrestfulapi.service;

import org.example.joblisting.exceptions.ResourcesNotFound;
import org.example.jobsrestfulapi.dto.PostDTO;
import org.example.jobsrestfulapi.model.Post;
import org.example.jobsrestfulapi.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.example.jobsrestfulapi.SequenceGeneratorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private SequenceGeneratorService sequenceGeneratorService;
    public PostService(PostRepository postRepository,SequenceGeneratorService sequenceGeneratorService){
        this.postRepository=postRepository;
        this.sequenceGeneratorService=sequenceGeneratorService;
    }
    public List<PostDTO> getAllPosts(){
        return this.postRepository.findAll()
                .stream()
                .map(s->new PostDTO(
                        s.getProfile(),
                        s.getDesc(),
                        s.getExp(),
                        s.getTechs()))
                .collect(Collectors.toList());
    }
    public PostDTO getPostById(String post_id){
        Post post =this.postRepository.findById(post_id)
                .orElseThrow(()->{throw new ResourcesNotFound("Post not found");
                });
        return new PostDTO(post.getProfile(),post.getDesc(),post.getExp(),post.getTechs());
    }
    public void addPost(PostDTO postDTO){
        System.out.println("Received DTO: " + postDTO);
        System.out.println("Teachs field: " + postDTO.getTechs());
        System.out.println("Teachs type: " + (postDTO.getTechs() != null ? postDTO.getTechs().getClass() : "null"));
        Post post=new Post(sequenceGeneratorService.generateSequence("post_sequence"),postDTO.getProfile(),postDTO.getDesc(),postDTO.getExp(),postDTO.getTechs());
        this.postRepository.save(post);
        System.out.println("Post object before save: " + post);
        this.postRepository.save(post);
    }
    public void updatePost(String post_id,PostDTO postDTO){
        Post post =this.postRepository.findById(post_id)
                .orElseThrow(()->{throw new ResourcesNotFound("Post not found");
                });
        post.setDesc(postDTO.getDesc());
        post.setExp(postDTO.getExp());
        post.setTechs(postDTO.getTechs());
        post.setProfile(post.getProfile());
    }

    public void deletePost(String post_id){
        Post post =this.postRepository.findById(post_id)
                .orElseThrow(()->{throw new ResourcesNotFound("Post not found");
                });
        this.postRepository.delete(post);

    }

}
