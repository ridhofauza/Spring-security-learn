/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.controller;

import com.testcoding.notesproject.model.dto.request.UserRequest;
import com.testcoding.notesproject.model.dto.response.LoginResponse;
import com.testcoding.notesproject.model.dto.response.UserResponse;
import com.testcoding.notesproject.service.LoginService;
import com.testcoding.notesproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@AllArgsConstructor
@RestController
public class AuthController {
    
    private LoginService loginService;
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest userReq) {
        return new ResponseEntity<>(loginService.login(userReq), HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userReq) {
        return new ResponseEntity<>(userService.create(userReq), HttpStatus.CREATED);
    }
    
}
