package com.rest.JobApp.repo;

import com.rest.JobApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
Users findByUserName(String username);
}
