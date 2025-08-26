package org.example.jobsrestfulapi.model;

import jakarta.validation.constraints.NotBlank;
import org.example.jobsrestfulapi.exception.ResourcesAlreadyFound;
import org.example.jobsrestfulapi.exception.ResourcesNotFound;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Company")
public class Company {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotBlank(message = "Company name is required")
    private String name;
    @NotBlank(message = "Description is required")

    private String desc;
    @NotBlank(message = "City  is required")

    private String city;
    private String url;
    @Field("image")
    private String image;
    private List<Post> posts;

    public Company(){

    }
    public Company(String name, String desc,
                   String city, String url, String image){
        this.name = name;
        this.desc=desc;
        this.city=city;
        this.url=url;
        this.image=image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public void addPost(Post post){
        if(this.posts==null){
            this.posts=new ArrayList<>();
        }
        boolean existPost=this.posts.stream().anyMatch(p ->p.getId().equals(post.getId()) );
        if(existPost)throw new ResourcesAlreadyFound("Post already exists");
        this.posts.add(post);
    }
    public void removePost(Post post){
        if(this.posts!=null){
            boolean existPost=this.posts.stream().anyMatch(p ->p.getId().equals(post.getId()) );
            if(!existPost)throw new ResourcesNotFound("Post not found");
            this.posts.remove(post);
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", city='" + city + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
