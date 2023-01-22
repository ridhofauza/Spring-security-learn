/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.controller;

import com.testcoding.notesproject.model.Comment;
import com.testcoding.notesproject.model.dto.request.CommentRequest;
import com.testcoding.notesproject.model.dto.response.CommentResponse;
import com.testcoding.notesproject.service.CommentService;
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
@RequestMapping("comment")
public class CommentController {
    
    private CommentService commentService;
    
    @GetMapping("/all/{idNote}")
    public ResponseEntity<List<CommentResponse>> getAll(@PathVariable Long idNote) {
        return new ResponseEntity<>(commentService.getAll(idNote), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getById(@PathVariable Long id) { 
        return new ResponseEntity<>(commentService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody CommentRequest commentReq) {
        return new ResponseEntity<>(commentService.create(commentReq), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody CommentRequest commentReq) {
        return new ResponseEntity<>(commentService.update(id, commentReq), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> delete(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.delete(id), HttpStatus.OK);
    }
    
}
