package com.mediaLaboSolutionsMicroRisque.controller;

import com.mediaLaboSolutionsMicroRisque.entity.AssessmentResult;
import com.mediaLaboSolutionsMicroRisque.service.AssessmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assess")
public class AssessmentController {

    private final AssessmentService service;

    public AssessmentController(AssessmentService service) {
        this.service = service;
    }

    @GetMapping("/{patientId}")
    public AssessmentResult assess(@PathVariable Integer patientId) {
        return service.assessmentResult(patientId);
    }
}
