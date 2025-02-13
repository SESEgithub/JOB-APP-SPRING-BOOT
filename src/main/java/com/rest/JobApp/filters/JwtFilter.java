package com.rest.JobApp.filters;

import com.rest.JobApp.service.JwtService;
import com.rest.JobApp.service.TheUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
@Autowired
    JwtService jwtService;
@Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerAuth=request.getHeader("Authorization");
        String token=null;
        String usrName=null;
        if (headerAuth!=null && headerAuth.startsWith("Bearer ")) {
            token = headerAuth.substring(7);
            usrName = jwtService.extractUserName(token);
        }
        if (usrName != null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails=context.getBean(TheUserDetailsService.class).loadUserByUsername(usrName);
            if (jwtService.validateToken(token , userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
              usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(request , response);
    }


}
