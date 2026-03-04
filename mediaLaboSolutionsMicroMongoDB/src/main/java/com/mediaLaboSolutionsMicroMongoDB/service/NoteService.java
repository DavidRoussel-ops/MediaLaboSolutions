package com.mediaLaboSolutionsMicroMongoDB.service;

import com.mediaLaboSolutionsMicroMongoDB.entity.Note;
import com.mediaLaboSolutionsMicroMongoDB.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant la logique métier des notes
 */
@Service
public class NoteService {

    private final NoteRepository repository;

    /**
     * Injection du repository Note
     * @param noteRepository
     */
    public NoteService(NoteRepository noteRepository) {
        this.repository = noteRepository;
    }

    /**
     * Récupère la liste complète des notes
     * @return List<Note>
     */
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    /**
     * Récupère une note par son id
     * @param id
     * @return Optional<Note>
     */
    public Optional<Note> getNoteById(String id) {
        return repository.findById(id);
    }

    /**
     * Récupère la liste des notes d'un patient
     * @param patId
     * @return List<Note>
     */
    public List<Note> getNotesByPatientId(Long patId) {
        return repository.findByPatId(patId);
    }

    /**
     * Cre une note
     * @param note
     * @return Note
     */
    public Note addNote(Note note) {
        return repository.save(note);
    }

    /**
     * Met à jour une note
     * @param id
     * @param update
     * @return Optional<Note>
     */
    public Optional<Note> updateNote(String id, Note update) {
        return repository.findById(id).map(note -> {
            note.setNote(update.getNote());
            return repository.save(update);
        });
    }

    /**
     * Supprime une note
     * @param id
     * @return boolean
     */
    public boolean deleteNote(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
