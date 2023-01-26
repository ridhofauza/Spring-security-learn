/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.controller;

import com.testcoding.notesproject.model.Note;
import com.testcoding.notesproject.model.dto.request.NoteRequest;
import com.testcoding.notesproject.model.dto.response.NoteResponse;
import com.testcoding.notesproject.service.NoteService;
import java.util.List;
import lombok.AllArgsConstructor;
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
@RequestMapping("note")
public class NoteController {
    
    private NoteService noteService;
    
    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAll() {
        return new ResponseEntity<>(noteService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<List<NoteResponse>> getAllByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(noteService.getByUserId(id), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(noteService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<NoteResponse> create(@RequestBody NoteRequest noteReq) {
        return new ResponseEntity<>(noteService.create(noteReq), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> update(@PathVariable Long id, @RequestBody NoteRequest noteReq) {
        return new ResponseEntity<>(noteService.update(id, noteReq), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<NoteResponse> delete(@PathVariable Long id) {
        return new ResponseEntity<>(noteService.delete(id), HttpStatus.OK);
    }
}
