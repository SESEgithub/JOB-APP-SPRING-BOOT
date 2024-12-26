package com.rest.JobApp.service;

import com.rest.JobApp.model.UserPrincipal;
import com.rest.JobApp.model.Users;
import com.rest.JobApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TheUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    public TheUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user=userRepo.findByUserName(userName);


        if (user==null){
            throw new UsernameNotFoundException(" user not found" +userName);
        }
        return new UserPrincipal(user);
    }
}
