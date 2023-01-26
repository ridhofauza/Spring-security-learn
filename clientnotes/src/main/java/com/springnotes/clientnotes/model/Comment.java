/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springnotes.clientnotes.model;

import java.time.LocalDateTime;
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
public class Comment {
    
    private Long id;
    private Long userId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
