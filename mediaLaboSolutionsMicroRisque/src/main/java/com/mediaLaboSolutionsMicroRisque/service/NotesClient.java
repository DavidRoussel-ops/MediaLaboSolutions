package com.mediaLaboSolutionsMicroRisque.service;

import com.mediaLaboSolutionsMicroRisque.entity.NoteDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * REST permettant de communiquer avec le microservice Notes
 */
@Service
public class NotesClient {
    
    private final RestTemplate restTemplate;

    /**
     * Injection du RestTemplate
     * @param restTemplate
     */
    public NotesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Récupère toute les notes associée à un patient
     * @param id
     * @return List<NoteDto>
     */
    public List<NoteDto> getNotesByPatient(Integer id) {
        return restTemplate.exchange(
                "http://notes-service:8082/api/notes/patient/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NoteDto>>() {}
        ).getBody();
    }
}
