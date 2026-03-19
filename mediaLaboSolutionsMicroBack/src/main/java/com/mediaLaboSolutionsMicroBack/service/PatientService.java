package com.mediaLaboSolutionsMicroBack.service;

import com.mediaLaboSolutionsMicroBack.entity.Adresse;
import com.mediaLaboSolutionsMicroBack.entity.Patient;
import com.mediaLaboSolutionsMicroBack.entity.Telephone;
import com.mediaLaboSolutionsMicroBack.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service gérant la logique métier des patients
 */
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    /**
     * Injection du repository Patient
     * @param patientRepository
     */
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Récupère la liste complète des patients
     * @return List<Patient>
     */
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * Récupère un patient par son id
     * @param id
     * @return Optional<Patient>
     */
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    /**
     * Récupère un ou plusieurs patient grace à lurs noms, prénoms et date de naissance
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @return List<Patient>
     */
    public List<Patient> getPatientByIdentity(String nom, String prenom, LocalDate dateNaissance) {
        return patientRepository.findByNomAndPrenomAndDateNaissance(nom, prenom, dateNaissance);
    }

    /**
     * Crée un patient
     * @param patient
     * @return Patient
     */
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Met à jour un patient
     * @param id
     * @param updated
     * @return Optional<Patient>
     */
    public Optional<Patient> updatePatient(Long id, Patient updated) {
        return patientRepository.findById(id).map(patient -> {
            patient.setNom(updated.getNom());
            patient.setPrenom(updated.getPrenom());
            patient.setDateNaissance(updated.getDateNaissance());
            patient.setGenre(updated.getGenre());
            if (patient.getAdresse() == null) {
                patient.setAdresse(new Adresse());
            }
            patient.getAdresse().setLibelle(updated.getAdresse().getLibelle());
            if (patient.getTelephone() == null) {
                patient.setTelephone(new Telephone());
            }
            patient.getTelephone().setNumero(updated.getTelephone().getNumero());
            return patientRepository.save(patient);
        });
    }

    /**
     * Supprime un patient
     * @param id
     * @return boolean
     */
    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
