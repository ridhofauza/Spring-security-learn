/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.repository;

import com.testcoding.notesproject.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface NoteRepository extends JpaRepository<Note, Long> { }
