package org.example.joblisting.Controller;

import org.example.joblisting.dto.PostDTO;
import org.example.joblisting.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private PostService postService;
    public PostController(PostService postService){
        this.postService=postService;
    }

    @GetMapping("/")
    public ResponseEntity getPosts(){
        return ResponseEntity.ok(this.postService.getAllPosts());
    }
    @GetMapping("/{id}")
    public ResponseEntity getPostById(@PathVariable(value = "id") String post_id){
        return ResponseEntity.ok(this.postService.getPostById(post_id));
    }
    @PostMapping("/")
    public ResponseEntity createPost(@RequestBody PostDTO postDTO){
        this.postService.addPost(postDTO);
        return ResponseEntity.ok("Successfully created new post");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable(value="id") String post_id)
    {
        this.postService.deletePost(post_id);
        return ResponseEntity.ok("Successfully deleted post");

    }
    @PutMapping("/{id}")
    public ResponseEntity updatePost(@PathVariable(value="id")String post_id,@RequestBody PostDTO postDTO){
        this.postService.updatePost(post_id,postDTO);
        return ResponseEntity.ok("Successfully updated post");
    }
}
