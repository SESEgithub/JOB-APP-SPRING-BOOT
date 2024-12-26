package com.rest.JobApp.controller;

import com.rest.JobApp.model.JobPost;
import com.rest.JobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class JobController {
    @Autowired
    private JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping("jobposts")
    public List<JobPost> getAllJobs(){

        return service.getAllJobs();
    }

    @GetMapping("jobposts/find/{key}")
    public List<JobPost> search(@PathVariable("key")  String key){
        return service.search(key);
    }


    @GetMapping("jobpost/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId){

        return service.getJob(postId);
    }

    @PostMapping("jobpost")
    public JobPost addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @PutMapping("jobpost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobpost/{postId}")
    public String deleteJob(@PathVariable int postId){
        service.deleteJob(postId);
        return "Successfully Deleted";
    }

}
