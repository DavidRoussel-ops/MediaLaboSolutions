package com.mediaLaboSolutionsMicroBack.controller;

import com.mediaLaboSolutionsMicroBack.entity.Genre;
import com.mediaLaboSolutionsMicroBack.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreController.class)
@AutoConfigureMockMvc
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GenreService service;

    private Genre genre;

    @BeforeEach
    public void setUp() {
        genre = new Genre(1L, "H");
        when(service.getAllGenre()).thenReturn(List.of(genre));
        when(service.getGenreById(1L)).thenReturn(Optional.of(genre));
        when(service.getGenreById(999L)).thenReturn(Optional.empty());
    }

    @Test
    public void testGetAllGenres() throws Exception {
        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].libelle").value("H"));
    }

    @Test
    public void testGetGenreByIdFound() throws Exception {
        mockMvc.perform(get("/api/genres/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value("H"));
    }

    @Test
    public void testGetGenreByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/genre/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateGenre() throws Exception {
        Genre created = new Genre(2L, "F");
        when(service.createGenre(any(Genre.class))).thenReturn(created);
        String jsonGenre = """
                {
                    "libelle" : "F"
                }
                """;

        mockMvc.perform(post("/api/genres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonGenre))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.libelle").value("F"));
    }

    @Test
    public void testUpdateGenreFound() throws Exception {
        Genre updated = new Genre(1L, "NonBinaire");
        when(service.updateGenre(eq(1L), any(Genre.class))).thenReturn(Optional.of(updated));
        Genre genre = service.getAllGenre().get(0);

        String jsonGenre = """
                {
                    "libelle" : "NonBinaire"
                }
                """;

        mockMvc.perform(put("/api/genres/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonGenre))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value("NonBinaire"));
    }

    @Test
    public void testUpdateGenreNotFound() throws Exception {
        when(service.updateGenre(eq(999L), any(Genre.class))).thenReturn(Optional.empty());
        String jsonGenre = """
                {
                    "libelle" : "NonBinaire"
                }
                """;

        mockMvc.perform(put("/api/genres/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonGenre))
                        .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteGenreFound() throws Exception {
        when(service.deleteGenre(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/genres/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteGenreNotFound() throws Exception {
        when(service.deleteGenre(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/genres/999"))
                .andExpect(status().isNotFound());
    }
}
