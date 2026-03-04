package com.mediaLaboSolutionsMicroBack.controller;

import com.mediaLaboSolutionsMicroBack.entity.Genre;
import com.mediaLaboSolutionsMicroBack.entity.Patient;
import com.mediaLaboSolutionsMicroBack.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientService service;

    private Patient patient;
    private Genre genre;

    @BeforeEach
    void setUp() {
        genre = new Genre(1L, "M");
        patient = new Patient(1L, "Dupont", "Michel",
                LocalDate.of(1990, 5, 5), genre, null, null);

        when(service.getAllPatients()).thenReturn(List.of(patient));
        when(service.getPatientById(1L)).thenReturn(Optional.of(patient));
        when(service.getPatientById(999L)).thenReturn(Optional.empty());
    }


    @Test
    public void testGetAllPatients() throws Exception {
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Dupont"));
    }

    @Test
    public void testGetPatientByIdFound() throws Exception {
        mockMvc.perform(get("/api/patients/1"))
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
        Patient savedPatient = new Patient( 1L, "Dupont", "Marcel",
                LocalDate.of(1990, 5, 5), genre, null, null );
        when(service.createPatient(any(Patient.class))).thenReturn(savedPatient);

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
        Patient patient = new Patient( 1L, "Dupont", "Pierre",
                LocalDate.of(1990, 5, 5), genre, null, null );
        when(service.updatePatient(eq(1L), any(Patient.class))).thenReturn(Optional.of(patient));

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

        mockMvc.perform(put("/api/patients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prenom").value("Pierre"));
    }

    @Test
    void testUpdatePatientNotFound() throws Exception {
        when(service.updatePatient(eq(999L), any(Patient.class))).thenReturn(Optional.empty());
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
        when(service.deletePatient(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/patients/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeletePatientNotFound() throws Exception {
        when(service.deletePatient(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/patients/999"))
                .andExpect(status().isNotFound());
    }
}
