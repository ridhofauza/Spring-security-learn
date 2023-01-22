/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.controller;

import com.springnotes.clientnotes.model.User;
import com.springnotes.clientnotes.model.dto.request.UserRequest;
import com.springnotes.clientnotes.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String loginForm(@RequestParam(required = false) boolean error, UserRequest userRequest, Model model) {
        model.addAttribute("error", error);
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(UserRequest loginReq) {
        try {
            ResponseEntity response = authService.login(loginReq);
            if (response.getStatusCode() == HttpStatus.OK) {
                return "redirect:/user";
            }
            return "redirect:/login?error=true";
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/register")
    public String registerForm(User user) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        authService.register(user);
        return "redirect:/register";
    }

}
