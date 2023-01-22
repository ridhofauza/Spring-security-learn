/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.service;

import com.springnotes.clientnotes.model.User;
import com.springnotes.clientnotes.model.dto.request.UserRequest;
import com.springnotes.clientnotes.model.dto.response.UserResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@Service
public class UserService {

    @Value("${server.baseUrl}/user")
    private String url;
    private RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAll() {
        return restTemplate
            .exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {
            }
            ).getBody();
    }
    
    public User getById(Long id) {
        return restTemplate
            .exchange(url + "/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<User>(){}
            ).getBody();
    }
    
    public User update(Long id, UserRequest userReq) {
        return restTemplate
            .exchange(url + "/" + id, HttpMethod.PUT, new HttpEntity(userReq), 
                new ParameterizedTypeReference<User>(){}
            ).getBody();
    }
    
    public User delete(Long id) {
        return restTemplate
            .exchange(url + "/" + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<User>(){}
            ).getBody();
    }

}
