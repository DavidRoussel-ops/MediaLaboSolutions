package com.mediaLaboSolutionsMicroRisque.controller;

import com.mediaLaboSolutionsMicroRisque.entity.AssessmentResult;
import com.mediaLaboSolutionsMicroRisque.entity.RiskLevel;
import com.mediaLaboSolutionsMicroRisque.service.AssessmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AssessmentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AssessmentService assessmentService;

    @Test
    public void assessmentResult200() throws Exception {
        AssessmentResult result = new AssessmentResult();
        result.setPatientId(1);
        result.setPatientName("Michel Dupont");
        result.setAge(45);
        result.setGender("M");
        result.setTriggerCount(3);
        result.setRiskLevel(RiskLevel.IN_DANGER);
        result.setMessage("Patient: Michel Dupont (45 ans) risque: IN_DANGER");

        when(assessmentService.assessmentResult(1)).thenReturn(result);

        mockMvc.perform(get("/api/assess/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId").value(1))
                .andExpect(jsonPath("$.patientName").value("Michel Dupont"))
                .andExpect(jsonPath("$.age").value(45))
                .andExpect(jsonPath("$.gender").value("M"))
                .andExpect(jsonPath("$.triggerCount").value(3))
                .andExpect(jsonPath("$.riskLevel").value("IN_DANGER"))
                .andExpect(jsonPath("$.message").value("Patient: Michel Dupont (45 ans) risque: IN_DANGER"));
    }

    @Test
    public void assessmentResult404() throws Exception {
        when(assessmentService.assessmentResult(99)).thenThrow(new RuntimeException("Patient not found"));

        mockMvc.perform(get("/api/assess/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void assessmentResult500() throws Exception {
        when(assessmentService.assessmentResult(1)).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/api/assess/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
