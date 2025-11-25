package com.mediaLaboSolutionsMicroBack.controller;

import com.mediaLaboSolutionsMicroBack.entity.Telephone;
import com.mediaLaboSolutionsMicroBack.repository.TelephoneRepository;
import com.mediaLaboSolutionsMicroBack.service.TelephoneService;
import com.mediaLaboSolutionsMicroBack.repository.PatientRepository;
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
public class TelephoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TelephoneRepository telephoneRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TelephoneService service;

    @BeforeEach
    public void setUp() {
        patientRepository.deleteAll();
        telephoneRepository.deleteAll();
        telephoneRepository.save(new Telephone(null, "123-555-6789"));
    }

    @Test
    public void testGetAllTelephones() throws Exception {
        mockMvc.perform(get("/api/telephones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].numero").value("123-555-6789"));
    }

    @Test
    public void testGetTelephoneByIdFound() throws Exception {
        Telephone telephone = service.getAllTelephones().get(0);

        mockMvc.perform(get("/api/telephones/" + telephone.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value("123-555-6789"));
    }

    @Test
    public void testGetTelephoneByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/telephones/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateTelephone() throws Exception {
        String jsonTelephone = """
                {
                    "numero" : "123-555-6789"
                }
                """;

        mockMvc.perform(post("/api/telephones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTelephone))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numero").value("123-555-6789"));
    }

    @Test
    public void testUpdateTelephoneFound() throws Exception {
        Telephone telephone = service.getAllTelephones().get(0);

        String jsonTelephone = """
                {
                    "numero" : "789-555-1234"
                }
                """;

        mockMvc.perform(put("/api/telephones/" + telephone.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTelephone))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value("789-555-1234"));
    }

    @Test
    public void testUpdateTelephoneNotFound() throws Exception {
        String jsonTelephone = """
                {
                    "numero" : "789-555-1234"
                }
                """;

        mockMvc.perform(put("/api/telephones/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTelephone))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteTelephoneFound() throws Exception {
        Telephone telephone = service.getAllTelephones().get(0);

        mockMvc.perform(delete("/api/telephones/" + telephone.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteTelephoneNotFound() throws Exception {
        mockMvc.perform(delete("/api/telephones/999"))
                .andExpect(status().isNotFound());
    }
}
