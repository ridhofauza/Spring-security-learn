/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.service;

import com.testcoding.notesproject.model.Role;
import com.testcoding.notesproject.model.dto.request.RoleRequest;
import com.testcoding.notesproject.repository.RoleRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author user
 */
@AllArgsConstructor
@Service
public class RoleService {
    
    private RoleRepository roleRepository;
    
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    
    public Role getById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    }
    
    public Role create(RoleRequest roleReq) {
        Role role = new Role();
        role.setName(roleReq.getName());
        return roleRepository.save(role);
    }
    
    public Role update(Long id, RoleRequest roleReq) {
        Role role = getById(id);
        role.setName(roleReq.getName());
        return roleRepository.save(role);
    }
    
    public Role delete(Long id) {
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }
    
    public Role findByName(String name) {
        return roleRepository.findByName(name)
            .orElse(null);
    }
    
    
} 
