/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.service;

import com.testcoding.notesproject.model.Comment;
import com.testcoding.notesproject.model.Note;
import com.testcoding.notesproject.model.User;
import com.testcoding.notesproject.model.dto.request.CommentRequest;
import com.testcoding.notesproject.model.dto.response.CommentResponse;
import com.testcoding.notesproject.repository.CommentRepository;
import com.testcoding.notesproject.util.DateConverter;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author user
 */
@AllArgsConstructor
@Service
public class CommentService {

    private CommentRepository commentRepository;

    public List<CommentResponse> getAll(Long idNote) {
        return commentRepository.findAllComment(idNote);
    }

    public Comment getById(Long id) {
        return commentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));
    }
    
    public Comment update(Long id, CommentRequest commentReq) {
        Comment comment =getById(id);
        User user = new User();
        user.setId(commentReq.getIdUser());
        comment.setComment(commentReq.getComment());
        LocalDateTime dateTime = new DateConverter(commentReq.getUpdatedAt()).toDateTime();
        comment.setUpdatedAt(dateTime);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    public Comment create(CommentRequest commentReq) {
        Comment comment = new Comment();
        User user = new User();
        user.setId(commentReq.getIdUser());
        comment.setUser(user);
        comment.setComment(commentReq.getComment());
        Note note = new Note();
        note.setId(commentReq.getIdNote());
        comment.setNote(note);
        LocalDateTime createdAt = new DateConverter(commentReq.getCreatedAt()).toDateTime();
        comment.setCreatedAt(createdAt);
        return commentRepository.save(comment);
    }
    
    public Comment delete(Long id) {
        Comment comment = getById(id);
        commentRepository.delete(comment);
        return comment;
    }

//    private CommentResponse commentResp(Comment comment) {
//        return new CommentResponse(
//            comment.getId(),
//            comment.getUser().getId(),
//            comment.getNote().getId(),
//            comment.getComment(),
//            comment.getCreatedAt(),
//            comment.getUpdatedAt()
//        );
//    }
}
