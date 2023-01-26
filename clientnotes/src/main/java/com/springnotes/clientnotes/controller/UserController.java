/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.controller;

import com.springnotes.clientnotes.model.User;
import com.springnotes.clientnotes.model.dto.request.UserRequest;
import com.springnotes.clientnotes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@AllArgsConstructor
@Controller
@RequestMapping("user")
public class UserController {
 
    private UserService userService;
    
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user/index";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/user";
    }
    
    @GetMapping("/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user/updateForm";
    }
    
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, UserRequest userReq) {
        userService.update(id, userReq);
        return "redirect:/user";
    }
    
    @ResponseBody
    @GetMapping("/coba")
    public Object getAll() {
        return userService.getAll();
    }
    
}
