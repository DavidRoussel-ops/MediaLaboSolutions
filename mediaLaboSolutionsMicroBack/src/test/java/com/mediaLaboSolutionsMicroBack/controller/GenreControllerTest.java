package com.mediaLaboSolutionsMicroBack.controller;

import com.mediaLaboSolutionsMicroBack.entity.Genre;
import com.mediaLaboSolutionsMicroBack.repository.GenreRepository;
import com.mediaLaboSolutionsMicroBack.repository.PatientRepository;
import com.mediaLaboSolutionsMicroBack.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private GenreService service;

    @BeforeEach
    public void setUp() {
        patientRepository.deleteAll();
        genreRepository.deleteAll();
        genreRepository.save(new Genre(null, "H"));
    }

    @Test
    public void testGetAllGenres() throws Exception {
        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].libelle").value("H"));
    }

    @Test
    public void testGetGenreByIdFound() throws Exception {
        Genre genre = service.getAllGenre().get(0);

        mockMvc.perform(get("/api/genres/" + genre.getId()))
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
        Genre genre = service.getAllGenre().get(0);

        String jsonGenre = """
                {
                    "libelle" : "NonBinaire"
                }
                """;

        mockMvc.perform(put("/api/genres/" + genre.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonGenre))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value("NonBinaire"));
    }

    @Test
    public void testUpdateGenreNotFound() throws Exception {
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
        Genre genre = service.getAllGenre().get(0);

        mockMvc.perform(delete("/api/genres/" + genre.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteGenreNotFound() throws Exception {
        mockMvc.perform(delete("/api/genres/999"))
                .andExpect(status().isNotFound());
    }
}
