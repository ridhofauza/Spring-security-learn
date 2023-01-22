/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.repository;

import com.testcoding.notesproject.model.Comment;
import com.testcoding.notesproject.model.dto.response.CommentResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author user
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // new CommentResponse()
//    @Query("SELECT new com.testcoding.notesproject.model.dto.response.CommentResponse(c.id, u.id, n.id, c.comment, c.createdAt, c.updatedAt) FROM Comment c JOIN c.note n JOIN n.user u WHERE n.id=?1")
    @Query("SELECT new com.testcoding.notesproject.model.dto.response.CommentResponse(c.id, u.id, c.comment, c.createdAt, c.updatedAt) FROM Comment c JOIN c.note n JOIN n.user u WHERE n.id=?1")    
    List<CommentResponse> findAllComment(Long idNote);
    
}
