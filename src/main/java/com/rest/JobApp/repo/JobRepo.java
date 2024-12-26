package com.rest.JobApp.repo;
import com.rest.JobApp.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost , Integer> {

     List<JobPost> findByPostProfileContainingOrPostDescriptionContaining(String postProfile, String postDescription);
}
