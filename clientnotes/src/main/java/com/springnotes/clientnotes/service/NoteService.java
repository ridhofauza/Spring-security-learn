/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.service;

import com.springnotes.clientnotes.model.Note;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@Service
public class NoteService {
    
    @Value("${server.baseUrl}/note")
    private String url;
    private RestTemplate restTemplate;

    public NoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Note> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null, 
            new ParameterizedTypeReference<List<Note>>(){}
        ).getBody();
    }
    
    public List<Note> getAllByUserId(Long userId) {
        return restTemplate.exchange(url + "/user/" + userId, HttpMethod.GET, null, 
            new ParameterizedTypeReference<List<Note>>(){}
        ).getBody();
    }
    
}
