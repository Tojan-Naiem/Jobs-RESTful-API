package org.example.jobsrestfulapi.controller;

import jakarta.validation.Valid;
import org.example.jobsrestfulapi.dto.PostDTO;
import org.example.jobsrestfulapi.model.Post;
import org.example.jobsrestfulapi.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private PostService postService;
    public PostController(PostService postService){
        this.postService=postService;
    }

    @GetMapping("/")
    public ResponseEntity getPosts(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "5") int size,
            @RequestParam(value = "text",required = false)String text
    ){
        if(text!=null&&!text.isEmpty()){
            Pageable pageable =PageRequest.of(page,size);
            Page<PostDTO>postDTOS=postService.searchByText(text,pageable);
            return ResponseEntity.ok(postDTOS);

        }
        Pageable pageable =PageRequest.of(page,size);
        Page<PostDTO>posts=postService.getAllPosts(pageable);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/{id}")
    public ResponseEntity getPostById(@PathVariable(value = "id") String post_id){
        return ResponseEntity.ok(this.postService.getPostById(post_id));
    }
    @PostMapping("/")
    public ResponseEntity createPost(@Valid @RequestBody PostDTO postDTO){
        Post post =this.postService.addPost(postDTO);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location)
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable(value="id") String post_id)
    {
        this.postService.deletePost(post_id);
        return ResponseEntity.ok("Successfully deleted post");

    }
    @PutMapping("/{id}")
    public ResponseEntity updatePost(@PathVariable(value="id")String post_id,@Valid @RequestBody PostDTO postDTO){
        this.postService.updatePost(post_id,postDTO);
        return ResponseEntity.ok("Successfully updated post");
    }
}
