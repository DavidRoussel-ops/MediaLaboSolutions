package com.mediaLaboSolutionsMicroBack.controller;

import com.mediaLaboSolutionsMicroBack.entity.Telephone;
import com.mediaLaboSolutionsMicroBack.service.TelephoneService;
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

@WebMvcTest(TelephoneController.class)
@AutoConfigureMockMvc
public class TelephoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TelephoneService service;

    private Telephone telephone;

    @BeforeEach
    public void setUp() {
        telephone = new Telephone(1L, "123-555-6789");
        when(service.getAllTelephones()).thenReturn(List.of(telephone));
        when(service.getTelephoneById(1L)).thenReturn(Optional.of(telephone));
        when(service.getTelephoneById(999L)).thenReturn(Optional.empty());
    }

    @Test
    public void testGetAllTelephones() throws Exception {
        mockMvc.perform(get("/api/telephones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numero").value("123-555-6789"));
    }

    @Test
    public void testGetTelephoneByIdFound() throws Exception {
        mockMvc.perform(get("/api/telephones/1"))
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
        Telephone created = new Telephone(2L, "123-555-6789");
        when(service.createTelephone(any(Telephone.class))).thenReturn(created);
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
        Telephone updated = new Telephone(1L, "789-555-1234");
        when(service.updateTelephone(eq(1L), any(Telephone.class))).thenReturn(Optional.of(updated));

        String jsonTelephone = """
                {
                    "numero" : "789-555-1234"
                }
                """;

        mockMvc.perform(put("/api/telephones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTelephone))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value("789-555-1234"));
    }

    @Test
    public void testUpdateTelephoneNotFound() throws Exception {
        when(service.updateTelephone(eq(999L), any(Telephone.class))).thenReturn(Optional.empty());
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
        when(service.deleteTelephone(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/telephones/" + telephone.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteTelephoneNotFound() throws Exception {
        when(service.deleteTelephone(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/telephones/999"))
                .andExpect(status().isNotFound());
    }
}
