package com.mediaLaboSolutionsMicroMongoDB.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediaLaboSolutionsMicroMongoDB.entity.Note;
import com.mediaLaboSolutionsMicroMongoDB.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NoteService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testAddNote() throws Exception {
        Note note = new Note();
        note.setPatId(1L);
        note.setPatient("TestNone");
        note.setNote("Le patient déclare qu'il se sent très bien.");

        Mockito.when(service.addNote(Mockito.any(Note.class))).thenReturn(note);

        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(note)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.patient").value("TestNone"));
    }

    @Test
    void testAddNoteBadRequest() throws Exception {
        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllNotes() throws Exception {
        Note note1 = new Note();
        note1.setPatId(1L);
        note1.setPatient("TestNone");

        Note note2 = new Note();
        note2.setPatId(2L);
        note2.setPatient("TestBorderline");

        List<Note> notes = Arrays.asList(note1, note2);

        Mockito.when(service.getAllNotes()).thenReturn(notes);

        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patient").value("TestNone"))
                .andExpect(jsonPath("$[1].patient").value("TestBorderline"));
    }

    @Test
    public void testGetNotesByPatient() throws Exception {
        Note note = new Note();
        note.setPatId(2L);
        note.setPatient("TestBorderline");

        Mockito.when(service.getNotesByPatientId(2L)).thenReturn(Arrays.asList(note));

        mockMvc.perform(get("/api/notes/patient/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patient").value("TestBorderline"));
    }

    @Test
    public void testGetNoteByIdFound() throws Exception {
        Note note = new Note();
        note.setId("123");
        note.setPatId(1L);
        note.setPatient("TestNone");

        Mockito.when(service.getNoteById("123")).thenReturn(Optional.of(note));

        mockMvc.perform(get("/api/notes/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patient").value("TestNone"));
    }

    @Test
    public void testGetNoteByIdNotFound() throws Exception {
        Mockito.when(service.getNoteById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/notes/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateNoteFound() throws Exception {
        Note updated = new Note();
        updated.setId("123");
        updated.setPatId(1L);
        updated.setPatient("TestNone");
        updated.setNote("Note mise à jour");

        Mockito.when(service.updateNote(Mockito.eq("123"), Mockito.any(Note.class)))
                .thenReturn(Optional.of(updated));

        mockMvc.perform(put("/api/notes/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.note").value("Note mise à jour"));
    }

    @Test
    public void testUpdateNoteNotFound() throws Exception {
        Note updated = new Note();
        updated.setId("999");
        updated.setPatId(1L);
        updated.setPatient("TestNone");

        Mockito.when(service.updateNote(Mockito.eq("999"), Mockito.any(Note.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/api/notes/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updated)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteNoteFound() throws Exception {
        Mockito.when(service.deleteNote("123")).thenReturn(true);

        mockMvc.perform(delete("/api/notes/123"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNoteNotFound() throws Exception {
        Mockito.when(service.deleteNote("999")).thenReturn(false);

        mockMvc.perform(delete("/api/notes/999"))
                .andExpect(status().isNotFound());
    }
}
