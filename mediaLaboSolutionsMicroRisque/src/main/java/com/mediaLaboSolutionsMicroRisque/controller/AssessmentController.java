package com.mediaLaboSolutionsMicroRisque.controller;

import com.mediaLaboSolutionsMicroRisque.entity.AssessmentResult;
import com.mediaLaboSolutionsMicroRisque.service.AssessmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST pour l'évaluation des risques
 */
@RestController
@RequestMapping("/api/assess")
public class AssessmentController {

    private final AssessmentService service;

    /**
     * Injection de AssessmentService
     * @param service
     */
    public AssessmentController(AssessmentService service) {
        this.service = service;
    }

    /**
     * Evaluation du risque à partir de l'identifiant d'un patient
     * @param patientId
     * @return AssessmentResult
     */
    @GetMapping("/{patientId}")
    public AssessmentResult assess(@PathVariable Integer patientId) {
        return service.assessmentResult(patientId);
    }
}
