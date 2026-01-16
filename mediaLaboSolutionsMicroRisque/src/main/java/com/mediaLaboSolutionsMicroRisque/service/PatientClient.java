package com.mediaLaboSolutionsMicroRisque.service;

import com.mediaLaboSolutionsMicroRisque.entity.PatientDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientClient {

    private final RestTemplate restTemplate;

    public PatientClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PatientDto getPatientById(Integer id) {
        return restTemplate.getForObject(
                "http://localhost:8081/patients/" + id,
                PatientDto.class
        );
    }
}
