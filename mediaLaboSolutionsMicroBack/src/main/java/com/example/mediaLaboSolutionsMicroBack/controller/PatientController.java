package com.example.mediaLaboSolutionsMicroBack.controller;

import com.example.mediaLaboSolutionsMicroBack.entity.Patient;
import com.example.mediaLaboSolutionsMicroBack.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/identity")
    public List<Patient> getPatientByIdentity(
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String dateNaissance) {
        return patientService.getPatientByIdentity(nom, prenom, LocalDate.parse(dateNaissance));
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updated) {
        return patientService.updatePatient(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
