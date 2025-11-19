package com.example.mediaLaboSolutionsMicroBack.controller;

import com.example.mediaLaboSolutionsMicroBack.entity.Adresse;
import com.example.mediaLaboSolutionsMicroBack.repository.AdresseRepository;
import com.example.mediaLaboSolutionsMicroBack.repository.PatientRepository;
import com.example.mediaLaboSolutionsMicroBack.service.AdresseService;
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
public class AdresseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AdresseService service;

    @BeforeEach
    public void setUp() {
        patientRepository.deleteAll();
        adresseRepository.deleteAll();
        adresseRepository.save(new Adresse(null, "1 rue de la liberté"));
    }

    @Test
    public void testGetAllAdresses() throws Exception {
        mockMvc.perform(get("/api/adresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].libelle").value("1 rue de la liberté"));
    }

    @Test
    public void testGetAdresseByIdFound() throws Exception {
        Adresse adresse = service.getAllAdresses().get(0);

        mockMvc.perform(get("/api/adresses/" + adresse.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value("1 rue de la liberté"));
    }

    @Test
    public void testGetAdresseByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/adresses/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateAdresse() throws Exception {
        String jsonAdresse = """
                {
                    "libelle" : "1 rue de la République"
                }
                """;

        mockMvc.perform(post("/api/adresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAdresse))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.libelle").value("1 rue de la République"));
    }

    @Test
    public void testUpdateAdresseFound() throws Exception {
        Adresse adresse = service.getAllAdresses().get(0);

        String jsonAdresse = """
                {
                    "libelle" : "1 rue de la République"
                }
                """;

        mockMvc.perform(put("/api/adresses/" + adresse.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAdresse))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value("1 rue de la République"));
    }

    @Test
    public void testUpdateAdresseNotFound() throws Exception {
        String jsonAdresse = """
                {
                    "libelle" : "1 rue de la République"
                }
                """;

        mockMvc.perform(put("/api/adresses/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAdresse))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteAdresseFound() throws Exception {
        Adresse adresse = service.getAllAdresses().get(0);

        mockMvc.perform(delete("/api/adresses/" + adresse.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteAdresseNotFound() throws Exception {
        mockMvc.perform(delete("/api/adresses/999"))
                .andExpect(status().isNotFound());
    }
}
