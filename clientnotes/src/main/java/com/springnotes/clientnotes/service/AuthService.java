/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.service;

import com.springnotes.clientnotes.model.User;
import com.springnotes.clientnotes.model.dto.request.UserRequest;
import com.springnotes.clientnotes.model.dto.response.LoginResponse;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@Service
public class AuthService {

    @Value("${server.baseUrl}")
    private String url;
    private RestTemplate restTemplate;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User register(UserRequest userReq) {
        System.out.println("REGISTER SERVICE");
        ResponseEntity<User> response = restTemplate
            .exchange(url + "/register", HttpMethod.POST, new HttpEntity(userReq),
                new ParameterizedTypeReference<User>() {
            });
        
        System.out.println("REGISTER SERVICE: " + response.getStatusCodeValue());
        return response.getBody();
    }

    public boolean login(UserRequest loginReq, HttpServletRequest request) {
        ResponseEntity<LoginResponse> loginResponse =  restTemplate
            .exchange(url + "/login", HttpMethod.POST, new HttpEntity(loginReq),
                new ParameterizedTypeReference<LoginResponse>() {
            });
        
        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            setAuthentication(loginResponse.getBody(), loginReq.getPassword());
            setSession(loginResponse.getBody(), request);
            return true;
        }
        return false;
    }
    
    public void setAuthentication(LoginResponse res, String password) {
        Collection<GrantedAuthority> authorities = res.getAuthorities()
            .stream().map(authority -> new SimpleGrantedAuthority(authority))
            .collect(Collectors.toList());
        
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            res.getUsername(),
            password,
            authorities
        );
        
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
    
    public void setSession(LoginResponse res, HttpServletRequest request) {
        request.getSession().setAttribute("userId", res.getId());
        request.getSession().setAttribute("username", res.getUsername());
        request.getSession().setAttribute("isLogin", true);
    }
    
    public void removeSession(HttpServletRequest request) {
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("isLogin");
    }

}
