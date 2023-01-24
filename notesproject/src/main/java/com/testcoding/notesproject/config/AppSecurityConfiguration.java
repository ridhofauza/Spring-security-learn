/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.config;

import com.testcoding.notesproject.service.AppUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author user
 */
@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    private AppUserDetailService appUserDetailService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }    

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(appUserDetailService)
            .passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/user").hasAnyRole("ADMIN")
            .antMatchers("/role/**").hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/user/{id}").hasAnyRole("ADMIN", "AUTHOR")
            .antMatchers(HttpMethod.PUT, "/user/{id}").hasAnyRole("ADMIN", "AUTHOR")
            .antMatchers(HttpMethod.DELETE, "/user/{id}").hasAnyRole("ADMIN", "AUTHOR")
            .antMatchers("/comment/**").hasAnyRole("ADMIN", "AUTHOR")
            .antMatchers("/note/**").hasAnyRole("ADMIN", "AUTHOR")
            .anyRequest().permitAll()
            .and()
            .httpBasic();
        
    }
    
    
}
