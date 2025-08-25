package org.example.jobsrestfulapi.service;

import org.example.jobsrestfulapi.exception.ResourcesNotFound;
import org.example.jobsrestfulapi.dto.PostDTO;
import org.example.jobsrestfulapi.model.Post;
import org.example.jobsrestfulapi.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService{

    private PostRepository postRepository;
    private final MongoTemplate mongoTemplate;

    public PostService(PostRepository postRepository,MongoTemplate mongoTemplate){
        this.postRepository=postRepository;
        this.mongoTemplate=mongoTemplate;
    }
    public Page<PostDTO> getAllPosts(Pageable pageable){
        return this.postRepository.findAll(pageable)
                .map(s->new PostDTO(
                        s.getProfile(),
                        s.getDesc(),
                        s.getExp(),
                        s.getTechs(),
                        s.getCompany()
                        )
                );
    }
    public PostDTO getPostById(String post_id){
        Post post =this.postRepository.findById(post_id)
                .orElseThrow(()->{throw new ResourcesNotFound("Post not found");
                });
        return new PostDTO(post.getProfile(),post.getDesc(),post.getExp(),post.getTechs(),post.getCompany());
    }
    public String addPost(PostDTO postDTO){

        Post post=new Post(postDTO.getProfile(),postDTO.getDesc(),postDTO.getExp(),postDTO.getTechs(),postDTO.getCompany());
        this.postRepository.save(post);
        return post.getId();
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
    public Page<PostDTO> searchByText(String text,Pageable pageable) {
        Query query = new Query();
        query.addCriteria(
                new Criteria().orOperator(
                        Criteria.where("desc").regex(text,"i"),
                        Criteria.where("profile").regex(text,"i")
                )
        );
        long total = mongoTemplate.count(query, Post.class);
        query.with(pageable);
        List<Post>posts=mongoTemplate.find(query, Post.class);

        List<PostDTO> dtoList = posts.stream()
                .map(s -> new PostDTO(s.getProfile(), s.getDesc(), s.getExp(), s.getTechs(),s.getCompany()))
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, total);
    }
    public void deletePost(String post_id){
        Post post =this.postRepository.findById(post_id)
                .orElseThrow(()->{throw new ResourcesNotFound("Post not found");
                });
        this.postRepository.delete(post);

    }

}
