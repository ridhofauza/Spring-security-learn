/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.service;

import com.springnotes.clientnotes.model.User;
import com.springnotes.clientnotes.model.dto.request.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    public User register(User user) {
        return restTemplate
            .exchange(url + "/user", HttpMethod.POST, new HttpEntity(user),
                new ParameterizedTypeReference<User>() {
            }
            ).getBody();
    }

    public ResponseEntity login(UserRequest loginReq) {
        return restTemplate
            .exchange(url + "/login", HttpMethod.POST, new HttpEntity(loginReq),
                new ParameterizedTypeReference<User>() {
            });
    }

}
