package com.mediaLaboSolutionsMicroMongoDB.service;

import com.mediaLaboSolutionsMicroMongoDB.entity.Note;
import com.mediaLaboSolutionsMicroMongoDB.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository noteRepository) {
        this.repository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return repository.findById(id);
    }

    public List<Note> getNotesByPatientId(Long patId) {
        return repository.findByPatId(patId);
    }

    public Note addNote(Note note) {
        return repository.save(note);
    }

    public Optional<Note> updateNote(Long id, Note update) {
        return repository.findById(id).map(note -> {
            note.setNote(update.getNote());
            return repository.save(update);
        });
    }

    public boolean deleteNote(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
