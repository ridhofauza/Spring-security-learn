/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.service;

import com.testcoding.notesproject.model.Role;
import com.testcoding.notesproject.model.User;
import com.testcoding.notesproject.model.dto.request.UserRequest;
import com.testcoding.notesproject.model.dto.request.UserRoleRequest;
import com.testcoding.notesproject.model.dto.response.UserResponse;
import com.testcoding.notesproject.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author user
 */
@AllArgsConstructor
@Service
public class UserService {
    
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleService roleService;
    
    public List<UserResponse> getAll() {
        return userRespList(userRepository.findAll());
    }
    
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found")
        );
        return userRespSingle(user);
    }
    
    public User getByIdWithoutDto(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found")
        );
    }
    
    public UserResponse create(UserRequest userReq) {
        User user = new User();
        List<Role> roles = new ArrayList<Role>();
        Role role = new Role();
        role.setId(2L); // Role ID 2 => author
        roles.add(role);
        user.setRoles(roles);
        user.setUsername(userReq.getUsername());
        user.setPassword(passwordEncoder.encode(userReq.getPassword()));
        return userRespSingle(userRepository.save(user));
    }
    
    public UserResponse update(Long id, UserRequest userReq) {
        User user = getByIdWithoutDto(id);
        user.setUsername(userReq.getUsername());
        String newPassword = userReq.getPassword().isEmpty() ? user.getPassword() : userReq.getPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRespSingle(userRepository.save(user));
    }
    
    public UserResponse delete(Long id) {
        User user = getByIdWithoutDto(id);
        userRepository.delete(user);
        return userRespSingle(user);
    }
    
    // Management Role
    public UserResponse addRole(Long idUser, UserRoleRequest userRoleReq) {
        User user = getByIdWithoutDto(idUser);
        List<Role> userRoles = user.getRoles();
        userRoleReq.getRoles().forEach(roleName -> {
            Role role = roleService.findByName(roleName);
            if(role != null && !userRoles.contains(role)) {
                userRoles.add(role);
            }
        });
        user.setRoles(userRoles);
        return userRespSingle(userRepository.save(user));
    }
    
    public UserResponse updateRole(Long idUser, UserRoleRequest userRoleReq) {
        User user = getByIdWithoutDto(idUser);
        List<Role> roles = new ArrayList<>();
        userRoleReq.getRoles().forEach(roleName -> {
                if(roleService.findByName(roleName) != null) { 
                    roles.add(roleService.findByName(roleName));
                }
            });
        user.setRoles(roles);
        return userRespSingle(userRepository.save(user));
    }
    
    public UserResponse deleteRole(Long idUser, UserRoleRequest userRoleReq) {
        User user = getByIdWithoutDto(idUser);
        List<Role> userRoles = user.getRoles();
        userRoleReq.getRoles().forEach(roleName -> {
            if(roleService.findByName(roleName) != null)
                userRoles.remove(roleService.findByName(roleName));
        });
        return userRespSingle(userRepository.save(user));
    }
    
    
    private List<UserResponse> userRespList(List<User> userList) {
        List<UserResponse> result = new ArrayList<>();
        for (User user : userList) {
            UserResponse userResp = new UserResponse();
            userResp.setId(user.getId());
            userResp.setUsername(user.getUsername());
            userResp.setPassword(user.getPassword());
            userResp.setRoles(user.getRoles());
            result.add(userResp);
        }
        return result;
    }
    
    private UserResponse userRespSingle(User user) {
        UserResponse userResp = new UserResponse();
        userResp.setId(user.getId());
        userResp.setUsername(user.getUsername());
        userResp.setPassword(user.getPassword());
        userResp.setRoles(user.getRoles());
        return userResp;
    }
    
}
