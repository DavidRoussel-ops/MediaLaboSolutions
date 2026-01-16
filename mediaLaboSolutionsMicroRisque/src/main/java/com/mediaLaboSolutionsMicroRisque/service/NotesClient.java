package com.mediaLaboSolutionsMicroRisque.service;

import com.mediaLaboSolutionsMicroRisque.entity.NoteDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotesClient {
    
    private final RestTemplate restTemplate;
    
    public NotesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<NoteDto> getNotesByPatient(Integer id) {
        return restTemplate.exchange(
                "http://localhost:8082/notes/patient/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NoteDto>>() {}
        ).getBody();
    }
}
