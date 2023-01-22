/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.service;

import com.testcoding.notesproject.model.User;
import com.testcoding.notesproject.model.dto.request.UserRequest;
import com.testcoding.notesproject.model.dto.response.LoginResponse;
import com.testcoding.notesproject.model.dto.response.UserResponse;
import com.testcoding.notesproject.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author user
 */
@AllArgsConstructor
@Service
public class LoginService {
    
    private AuthenticationManager authenticationManager;
    private AppUserDetailService appUserDetailService;
    private UserRepository userRepository;
    
//    // findByUsername
//    public UserResponse login(UserRequest userReq) {
//        User user = userRepository.findByUsername(userReq.getUsername())
//            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//        
//        if(!userReq.getPassword().equals(user.getPassword())) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Invalid password");
//        }
//        
//       return new UserResponse(
//           user.getId(),
//           user.getUsername(),
//           user.getPassword(),
//           user.getRoles()
//       );
//    }
    
    public LoginResponse login(UserRequest userReq) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
            userReq.getUsername(), userReq.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        UserDetails userDetail = appUserDetailService.loadUserByUsername(userReq.getUsername());
        User user = userRepository.findByUsername(userReq.getUsername()).get();
        
        List<String> authorities = userDetail.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .collect(Collectors.toList());
        return new LoginResponse(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            authorities
        );
    }
    
}
