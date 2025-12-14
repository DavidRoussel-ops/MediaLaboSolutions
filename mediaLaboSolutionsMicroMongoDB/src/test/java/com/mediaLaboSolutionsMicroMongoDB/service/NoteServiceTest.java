package com.mediaLaboSolutionsMicroMongoDB.service;

import com.mediaLaboSolutionsMicroMongoDB.entity.Note;
import com.mediaLaboSolutionsMicroMongoDB.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NoteServiceTest {

    @Mock
    private NoteRepository repository;

    @InjectMocks
    private NoteService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNote() {
        Note note = new Note();
        note.setPatId(1L);
        note.setPatient("TestNone");
        note.setNote("Le patient déclare que tout vas bien.");

        when(repository.save(note)).thenReturn(note);

        Note saved = service.addNote(note);

        assertNotNull(saved);
        assertEquals("TestNone", saved.getPatient());
        verify(repository, times(1)).save(note);
    }

    @Test
    public void testGetNoteById() {
        Note note = new Note();
        note.setId("1");
        note.setPatId(1L);
        note.setPatient("TestNone");

        when(repository.findById("1")).thenReturn(Optional.of(note));

        Optional<Note> found = service.getNoteById("1");

        assertNotNull(found);
        assertEquals("TestNone", found.get().getPatient());
    }

    @Test
    public void testGetNoteByIdKO() {
        when(repository.findById("99")).thenReturn(Optional.empty());

        Optional<Note> found = service.getNoteById("99");

        assertFalse(found.isPresent());
    }

    @Test
    public void testGetNotesByPatientId() {
        Note note1 = new Note();
        note1.setPatId(1L);
        note1.setPatient("TestNone");

        Note note2 = new Note();
        note2.setPatId(1L);
        note2.setPatient("TestNone");

        when(repository.findByPatId(1L)).thenReturn(Arrays.asList(note1, note2));

        List<Note> notes = service.getNotesByPatientId(1L);

        assertEquals(2, notes.size());
        assertEquals("TestNone", notes.get(0).getPatient());
    }

    @Test
    public void testGetNotesByPatientIdKO() {
        when(repository.findByPatId(99L)).thenReturn(Collections.emptyList());

        List<Note> notes = service.getNotesByPatientId(99L);

        assertTrue(notes.isEmpty());
    }

    @Test
    public void testUpdateNote() {
        Note existing = new Note();
        existing.setId("1");
        existing.setPatId(1L);
        existing.setPatient("TestNone");
        existing.setNote("Le patient déclare que tout vas bien.");

        Note updated = new Note();
        updated.setId("1");
        updated.setPatId(1L);
        updated.setPatient("TestNone");
        updated.setNote("Le patient déclare que tout ne vas plus bien.");

        when(repository.findById("1")).thenReturn(Optional.of(existing));
        when(repository.save(updated)).thenReturn(updated);

        Optional<Note> result = service.updateNote("1", updated);

        assertEquals("Le patient déclare que tout ne vas plus bien.", result.get().getNote());
        verify(repository, times(1)).save(updated);
    }

    @Test
    public void testUpdateNoteKO() {
        Note updated = new Note();
        updated.setId("99");
        updated.setPatId(1L);
        updated.setPatient("TestNone");
        updated.setNote("Le patient déclare que tout vas bien.");

        when(repository.findById("99")).thenReturn(Optional.empty());

        Optional<Note> result = service.updateNote("99", updated);

        assertFalse(result.isPresent());
        verify(repository, never()).save(any());
    }

    @Test
    public void testDeleteNote() {
        when(repository.existsById("1")).thenReturn(true);
        doNothing().when(repository).deleteById("1");

        service.deleteNote("1");

        verify(repository, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteNoteKO() {
        when(repository.existsById("99")).thenReturn(false);

        service.deleteNote("99");

        verify(repository, never()).deleteById(any());
    }
}
