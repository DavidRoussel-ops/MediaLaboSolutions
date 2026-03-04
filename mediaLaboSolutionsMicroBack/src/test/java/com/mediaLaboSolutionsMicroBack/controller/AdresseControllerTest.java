package com.mediaLaboSolutionsMicroBack.controller;

import com.mediaLaboSolutionsMicroBack.entity.Adresse;
import com.mediaLaboSolutionsMicroBack.service.AdresseService;
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

@WebMvcTest(AdresseController.class)
@AutoConfigureMockMvc
public class AdresseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AdresseService service;

    private Adresse adresse;

    @BeforeEach
    public void setUp() {
        adresse = new Adresse(1L, "1 rue de la liberté");
        when(service.getAllAdresses()).thenReturn(List.of(adresse));
        when(service.getAdresseById(1L)).thenReturn(Optional.of(adresse));
        when(service.getAdresseById(999L)).thenReturn(Optional.empty());
    }

    @Test
    public void testGetAllAdresses() throws Exception {
        mockMvc.perform(get("/api/adresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].libelle").value("1 rue de la liberté"));
    }

    @Test
    public void testGetAdresseByIdFound() throws Exception {
        mockMvc.perform(get("/api/adresses/1"))
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
        Adresse created = new Adresse(2L, "1 rue de la République");
        when(service.createAdresse(any(Adresse.class))).thenReturn(created);

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
        Adresse updated = new Adresse(1L, "1 rue de la République");
        when(service.updateAdresse(eq(1L), any(Adresse.class))).thenReturn(Optional.of(updated));

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
        when(service.updateAdresse(eq(999L), any(Adresse.class))).thenReturn(Optional.empty());

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
        when(service.deleteAdresse(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/adresses/" + adresse.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteAdresseNotFound() throws Exception {
        when(service.deleteAdresse(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/adresses/999"))
                .andExpect(status().isNotFound());
    }
}
