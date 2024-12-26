package com.rest.JobApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Component
@Entity
public class JobPost {
    @Id
private int postId;
private String postProfile;
private String postDescription;
private int reqExperience;
private List<String> postTeckStack;

    public JobPost() {
    }

    public JobPost(int postId, String postProfile, String postDescription, int reqExperience, List<String> postTeckStack) {
        this.postId = postId;
        this.postProfile = postProfile;
        this.postDescription = postDescription;
        this.reqExperience = reqExperience;
        this.postTeckStack = postTeckStack;
    }
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostProfile() {
        return postProfile;
    }

    public void setPostProfile(String postProfile) {
        this.postProfile = postProfile;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public int getReqExperience() {
        return reqExperience;
    }

    public void setReqExperience(int reqExperience) {
        this.reqExperience = reqExperience;
    }

    public List<String> getPostTeckStack() {
        return postTeckStack;
    }

    public void setPostTeckStack(List<String> postTeckStack) {
        this.postTeckStack = postTeckStack;
    }
}
