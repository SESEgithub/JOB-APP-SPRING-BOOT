package com.rest.JobApp.service;

import com.rest.JobApp.model.JobPost;
import com.rest.JobApp.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepo jobRepo;

    public JobService(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }


    public List<JobPost> getAllJobs(){
//  int x=5/0;
        return jobRepo.findAll();
    }

    public JobPost getJob(int postId) {
       return jobRepo.findById(postId).orElse(new JobPost());
    }

    public void addJob(JobPost jobPost){
        jobRepo.save(jobPost);
    }

    public void updateJob(JobPost jobPost) {
        jobRepo.save(jobPost);
    }

    public void deleteJob(int postId) {
        jobRepo.deleteById(postId);
    }

    public List<JobPost> search(String key) {
return jobRepo.findByPostProfileContainingOrPostDescriptionContaining(key,key);
    }
}
