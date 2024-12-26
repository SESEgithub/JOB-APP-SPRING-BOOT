package com.rest.JobApp.service;

import com.rest.JobApp.model.Users;
import com.rest.JobApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    @Autowired
   private UserRepo userRepo;
private final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);

    public UserService(UserRepo usersRepo) {
        this.userRepo = usersRepo;
    }



    public void addUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }




}
