/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.controller;

import com.springnotes.clientnotes.model.User;
import com.springnotes.clientnotes.model.dto.request.UserRequest;
import com.springnotes.clientnotes.service.AuthService;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author user
 */
@AllArgsConstructor
@Controller
public class AuthController {

    private AuthService authService;

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) boolean error, UserRequest userRequest, Model model, HttpServletRequest request) {
        if(request.getSession().getAttribute("isLogin") != null) {
            return "redirect:/note";
        }
    
        model.addAttribute("error", error);
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(UserRequest loginReq, HttpServletRequest request) {
        if(!authService.login(loginReq, request)) {
            return "redirect:/login?error=true";
        }
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities()
            .stream().map(authority -> authority.getAuthority())
            .collect(Collectors.toList());
        
        System.out.println("LOGIN INFO: "+authentication.getDetails());
        System.out.println("ROLES: "+roles);
        return "redirect:/note";
//        try {
//            ResponseEntity response = authService.login(loginReq);
//            if (response.getStatusCode() == HttpStatus.OK) {
//                return "redirect:/user";
//            }
//            return "redirect:/login?error=true";
//        } catch (HttpClientErrorException e) {
//            e.printStackTrace();
//            return "redirect:/login?error=true";
//        }
    }

    @GetMapping("/register")
    public String registerForm(UserRequest userReq, HttpServletRequest request) {
        if(request.getSession().getAttribute("isLogin") != null) {
            return "redirect:/note";
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(UserRequest userReq) {
        authService.register(userReq);
        return "redirect:/register";
    }
    
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        authService.removeSession(request);
    }

}
