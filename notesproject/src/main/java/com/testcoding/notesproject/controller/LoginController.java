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
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@AllArgsConstructor
@RestController
public class LoginController {
    
    private LoginService loginService;
    
//    @PostMapping("/login")
//    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userReq) {
//        return new ResponseEntity<>(loginService.login(userReq), HttpStatus.OK);
//    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest userReq) {
        return new ResponseEntity<>(loginService.login(userReq), HttpStatus.OK);
    }
    
}
