package com.rest.JobApp.controller;

import com.rest.JobApp.model.Users;
import com.rest.JobApp.service.UserService;
import com.rest.JobApp.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
   @Autowired
    private UserService usersService;
   @Autowired
    private AuthenticationManager authenticationManager;
   @Autowired
   private JwtService jwtService;

    public UserController(UserService usersService) {
        this.usersService = usersService;
    }

@GetMapping("session")
    public String getSessioId(HttpServletRequest request){
        return "Session Id: "+request.getSession().getId();
    }
    @PostMapping("registerUser")
    public Users addUser(@RequestBody Users users){
       usersService.addUser(users);
       return users;
    }

    @PostMapping("login")
    public String logIn(@RequestBody Users users){
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(users.getUserName(),users.getPassword()));
         if (authentication.isAuthenticated())
             return jwtService.generateToken( users.getUserName());

         else
             return " failed to login";


    }

}
