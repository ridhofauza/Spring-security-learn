/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testcoding.notesproject.service;

import com.testcoding.notesproject.model.Comment;
import com.testcoding.notesproject.model.Note;
import com.testcoding.notesproject.model.User;
import com.testcoding.notesproject.model.dto.request.NoteRequest;
import com.testcoding.notesproject.model.dto.response.CommentResponse;
import com.testcoding.notesproject.model.dto.response.NoteResponse;
import com.testcoding.notesproject.repository.NoteRepository;
import com.testcoding.notesproject.util.DateConverter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class NoteService {

    private UserService userService;
    private NoteRepository noteRepository;

    public List<NoteResponse> getAll() {
        return noteRespList(noteRepository.findAll());
    }

    public NoteResponse getById(Long id) {
        Note note = noteRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
        return noteRespSingle(note);
    }

    public Note getByIdWithoutDto(Long id) {
        return noteRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
    }

    public NoteResponse create(NoteRequest noteReq) {
        Note note = new Note();
        User user = userService.getByIdWithoutDto(noteReq.getUserId());
        note.setText(noteReq.getText());
        note.setUser(user);
        LocalDateTime createdAt = new DateConverter(noteReq.getCreatedAt()).toDateTime();
        note.setCreatedAt(createdAt);
        return noteRespSingle(noteRepository.save(note));
    }

    public NoteResponse update(Long id, NoteRequest noteReq) {
        User user = userService.getByIdWithoutDto(noteReq.getUserId());
        Note note = getByIdWithoutDto(id);
        note.setUser(user);
        note.setText(noteReq.getText());
        LocalDateTime updatedAt = new DateConverter(noteReq.getUpdatedAt()).toDateTime();
        note.setUpdatedAt(updatedAt);
        return noteRespSingle(noteRepository.save(note));
    }

    public NoteResponse delete(Long id) {
        Note note = getByIdWithoutDto(id);
        noteRepository.delete(note);
        return noteRespSingle(note);
    }

    private List<NoteResponse> noteRespList(List<Note> listNote) {
        List<NoteResponse> result = new ArrayList<>();
        for (Note note : listNote) {
            NoteResponse noteResp = new NoteResponse();
            noteResp.setId(note.getId());
            noteResp.setUserId(note.getUser().getId());
            noteResp.setText(note.getText());
            noteResp.setComments(note.getComments());
            noteResp.setCreatedAt(note.getCreatedAt());
            noteResp.setUpdatedAt(note.getUpdatedAt());
            result.add(noteResp);
        }
        return result;
    }

    private NoteResponse noteRespSingle(Note note) {
        NoteResponse noteResp = new NoteResponse();
        noteResp.setId(note.getId());
        noteResp.setUserId(note.getUser().getId());
        noteResp.setText(note.getText());
        noteResp.setComments(note.getComments());
        noteResp.setCreatedAt(note.getCreatedAt());
        noteResp.setUpdatedAt(note.getUpdatedAt());
        return noteResp;
    }

}
