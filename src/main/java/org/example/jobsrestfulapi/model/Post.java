package org.example.jobsrestfulapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "JobPost")
public class Post {

    @Id
    private String id;
    private String profile;
    private String desc;
    private int exp;
    private List<String> techs;
    private Company company;

    public Post(String profile,String desc,int exp,List<String> techs,Company company) {
    this.profile=profile;
    this.desc=desc;
    this.exp=exp;
    this.techs=techs;
    this.company=company;
    }
    public Post(String id,String profile,String desc,int exp,List<String> techs,Company company) {
        this.id=id;
        this.profile=profile;
        this.desc=desc;
        this.exp=exp;
        this.techs=techs;
        this.company=company;
    }
    public Post() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public List<String> getTechs() {
        return techs;
    }

    public void setTechs(List<String> techs) {
        this.techs = this.techs;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Post{" +
                "profile='" + profile + '\'' +
                ", desc='" + desc + '\'' +
                ", exp=" + exp +
                ", teach=" +techs +
                '}';
    }
}
