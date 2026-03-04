package com.mediaLaboSolutionsMicroRisque.service;

import com.mediaLaboSolutionsMicroRisque.entity.PatientDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * REST permettant de communiquer avec le microservice Patient
 */
@Service
public class PatientClient {

    private final RestTemplate restTemplate;

    /**
     * Injection du RestTemplate
     * @param restTemplate
     */
    public PatientClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Récupère les informations d'un patient
     * @param id
     * @return PatientDto
     */
    public PatientDto getPatientById(Integer id) {
        return restTemplate.getForObject(
                "http://patient-service:8081/api/patients/" + id,
                PatientDto.class
        );
    }
}
