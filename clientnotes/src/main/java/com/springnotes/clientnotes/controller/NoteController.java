/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.controller;

import com.springnotes.clientnotes.model.Note;
import com.springnotes.clientnotes.service.NoteService;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        model.addAttribute("notes", noteService.getAll());
        return "note/index";
    }
    
    @GetMapping("/user/{id}")
    public String myNote(@PathVariable Long id, Model model, HttpServletRequest request) {
        model.addAttribute("notes", noteService.getAllByUserId(id));
        return "note/mynote";
    }
    
    @GetMapping("/create")
    public String createNote(Note note) {
        return "note/create";
    }
    
    @PostMapping
    public String createdNote(Note note, HttpServletRequest request) {
        noteService.create(note);
        return "redirect:/note/user/"+request.getSession().getAttribute("userId");
    }
    
    @GetMapping("/update/{id}")
    public String updateNote(@PathVariable Long id, Model model) {
        model.addAttribute("note", noteService.getById(id));
        return "note/update";
    }
    
    @PutMapping("/{id}")
    public String updatedNote(@PathVariable Long id, Note note, HttpServletRequest request) {
        noteService.update(id, note);
        return "redirect:/note/user/"+request.getSession().getAttribute("userId");
    }
    
    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id, HttpServletRequest request) {
        noteService.delete(id);
        return "redirect:/note/user/"+request.getSession().getAttribute("userId");
    }
    
}
