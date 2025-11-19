package com.example.mediaLaboSolutionsMicroBack.controller;

import com.example.mediaLaboSolutionsMicroBack.entity.Genre;
import com.example.mediaLaboSolutionsMicroBack.entity.Patient;
import com.example.mediaLaboSolutionsMicroBack.repository.GenreRepository;
import com.example.mediaLaboSolutionsMicroBack.repository.PatientRepository;
import com.example.mediaLaboSolutionsMicroBack.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService service;

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    public void setUp() {
        patientRepository.deleteAll();
        genreRepository.deleteAll();
        Genre genre = genreRepository.save(new Genre(null, "M"));
        patientRepository.save(new Patient(null, "Dupont", "Michel", LocalDate.of(1990,5,5), genre, null, null));
    }

    @Test
    public void testGetAllPatients() throws Exception {
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Dupont"));
    }

    @Test
    public void testGetPatientByIdFound() throws Exception {
        Patient patient = service.getAllPatients().get(0);

        mockMvc.perform(get("/api/patients/" + patient.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prenom").value("Michel"));
    }

    @Test
    public void testGetPatientByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/patients/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePatient() throws Exception {
        Genre genre = genreRepository.findAll().get(0);

        String jsonPatient = """
                {
                    "nom" : "Dupont",
                    "prenom" : "Marcel",
                    "dateNaissance" : "1990-05-05",
                    "genre" : {
                        "id" : %d
                    }
                }
                """.formatted(genre.getId());

        mockMvc.perform(post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPatient))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.prenom").value("Marcel"));
    }

    @Test
    void testUpdatePatient() throws Exception {
        Patient patient = service.getAllPatients().get(0);
        Genre genre = genreRepository.findAll().get(0);

        String json = """
                {
                  "nom": "Dupont",
                  "prenom": "Pierre",
                  "dateNaissance": "1990-05-05",
                  "genre" : {
                        "id" : %d
                    }
                }
                """.formatted(genre.getId());

        mockMvc.perform(put("/api/patients/" + patient.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prenom").value("Pierre"));
    }

    @Test
    void testUpdatePatientNotFound() throws Exception {
        String json = """
                {
                  "nom": "Martin",
                  "prenom": "Pierre",
                  "dateNaissance": "1992-03-15"
                }
                """;

        mockMvc.perform(put("/api/patients/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void testDeletePatientFound() throws Exception {
        Patient patient = service.getAllPatients().get(0);

        mockMvc.perform(delete("/api/patients/" + patient.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeletePatientNotFound() throws Exception {
        mockMvc.perform(delete("/api/patients/999"))
                .andExpect(status().isNotFound());
    }
}
