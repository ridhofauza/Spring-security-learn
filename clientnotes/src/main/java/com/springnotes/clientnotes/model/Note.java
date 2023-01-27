/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author user
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    
    private Long id;
    private Long userId;
    private String username;
    private String text;
    private String createdAt;
    private String updatedAt;
    private List<Comment> comments;
    
}
