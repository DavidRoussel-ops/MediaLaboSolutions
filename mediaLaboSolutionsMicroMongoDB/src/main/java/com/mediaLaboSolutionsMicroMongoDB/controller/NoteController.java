package com.mediaLaboSolutionsMicroMongoDB.controller;

import com.mediaLaboSolutionsMicroMongoDB.entity.Note;
import com.mediaLaboSolutionsMicroMongoDB.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour le CRUD des notes
 */
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService service;

    /**
     * Constructeur noteService
     * @param noteService
     */
    public NoteController(NoteService noteService) {
        this.service = noteService;
    }

    /**
     * Crée une nouvellle note
     * @param note
     * @return ResponseEntity<Note>
     */
    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        if (note == null || note.getPatient() == null || note.getNote() == null) {
            return ResponseEntity.badRequest().build();
        }
        Note saved = service.addNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Récupère la liste des notes complète
     * @return List<Note>
     */
    @GetMapping
    public List<Note> getAllNotes() {return service.getAllNotes();}

    /**
     * Récupère la liste des notes d'un patient
     * @param patId
     * @return List<Note>
     */
    @GetMapping("/patient/{patId}")
    public List<Note> getNotesByPatient(@PathVariable Long patId) {
        return service.getNotesByPatientId(patId);
    }

    /**
     * Récupère un note par son id
     * @param id
     * @return ResponseEntity<Note>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        return service.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Met à jour une note
     * @param id
     * @param note
     * @return ResponseEntity<Note>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id, @RequestBody Note note) {
        return service.updateNote(id, note)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime une note
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        return service.deleteNote(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
