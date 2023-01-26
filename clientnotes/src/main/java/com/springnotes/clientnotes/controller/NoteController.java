/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.controller;

import com.springnotes.clientnotes.service.NoteService;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@AllArgsConstructor
@Controller
@RequestMapping("note")
public class NoteController {
    
    private NoteService noteService;
    
    @GetMapping
    public String index(Model model, HttpServletRequest request) {
//        model.addAttribute("userId", request.getSession().getAttribute("userId"));
        model.addAttribute("notes", noteService.getAll());
        return "note/index";
    }
    
    @GetMapping("/user/{id}")
    public String myNote(@PathVariable Long id, Model model, HttpServletRequest request) {
//        model.addAttribute("userId", request.getSession().getAttribute("userId"));
        model.addAttribute("notes", noteService.getAllByUserId(id));
        return "note/mynote";
    }
    
}
