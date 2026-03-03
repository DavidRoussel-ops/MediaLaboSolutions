package com.mediaLaboSolutionsMicroBack.controller;

import com.mediaLaboSolutionsMicroBack.entity.Patient;
import com.mediaLaboSolutionsMicroBack.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    /**
     * Constructeur patientService
     * @param patientService
     */
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Récupère la liste des patients
     * @return List<Patient>
     */
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    /**
     * Récupère un patient par son id
     * @param id
     * @return ResponseEntity<Patient>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Récupère une list de patient grace à leur noms, prénoms et date de naissances
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @return List<Patient>
     */
    @GetMapping("/identity")
    public List<Patient> getPatientByIdentity(
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String dateNaissance) {
        return patientService.getPatientByIdentity(nom, prenom, LocalDate.parse(dateNaissance));
    }

    /**
     * Crée un nouveau patient
     * @param patient
     * @return ResponseEntity<Patient>
     */
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient saved = patientService.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Met à jour un patient
     * @param id
     * @param updated
     * @return ResponseEntity<Patient>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updated) {
        return patientService.updatePatient(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime un patient
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
