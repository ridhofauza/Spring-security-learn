/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_note")
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String text;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    
    @Column(columnDefinition = "DATETIME NULL")
    private LocalDateTime createdAt;
    
    @Column(columnDefinition = "DATETIME NULL")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
}
