/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.config;

import com.springnotes.clientnotes.utils.RequestInterceptor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        
        if(CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        
        interceptors.add(new RequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
    
}
