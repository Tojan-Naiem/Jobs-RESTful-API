package org.example.jobsrestfulapi.dto;

import org.example.jobsrestfulapi.model.Company;

import java.util.ArrayList;
import java.util.List;

public class PostDTO {
    private String profile;
    private String desc;
    private int exp;
    private List<String> techs;
    private String companyId;

    public PostDTO(String profile,String desc,int exp,List<String> techs,String companyId) {
        this.profile=profile;
        this.desc=desc;
        this.exp=exp;
        this.techs=techs;
        this.companyId=companyId;
    }
    public PostDTO() {

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
        this.techs = techs;
    }

    public String getCompany() {
        return companyId;
    }

    public void setCompany(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "profile='" + profile + '\'' +
                ", desc='" + desc + '\'' +
                ", exp=" + exp +
                ", teach=" + techs +
                '}';
    }
}
