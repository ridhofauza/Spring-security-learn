/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.controller;

import com.testcoding.notesproject.model.User;
import com.testcoding.notesproject.model.dto.request.UserRequest;
import com.testcoding.notesproject.model.dto.request.UserRoleRequest;
import com.testcoding.notesproject.model.dto.response.UserResponse;
import com.testcoding.notesproject.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@AllArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {        
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }
    
//    @PostMapping
//    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userReq) {
//        return new ResponseEntity<>(userService.create(userReq), HttpStatus.CREATED);
//    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest userReq) {
        return new ResponseEntity<>(userService.update(id, userReq), HttpStatus.OK);
    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable Long id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
    
    @PostMapping("/{userId}/role")
    public ResponseEntity<UserResponse> addRole(@PathVariable Long userId, @RequestBody UserRoleRequest userRoleReq) {
        return new ResponseEntity<>(userService.addRole(userId, userRoleReq), HttpStatus.OK);
    }
    
    @PutMapping("/{userId}/role")
    public ResponseEntity<UserResponse> updateRole(@PathVariable Long userId, @RequestBody UserRoleRequest userRoleReq) {
        return new ResponseEntity<>(userService.updateRole(userId, userRoleReq), HttpStatus.OK);
    }
    
    @DeleteMapping("/{userId}/role")
    public ResponseEntity<UserResponse> deleteRole(@PathVariable Long userId, @RequestBody UserRoleRequest userRoleReq) {
        return new ResponseEntity<>(userService.deleteRole(userId, userRoleReq), HttpStatus.OK);
    }
    
}
