package com.mediaLaboSolutionsMicroBack.service;

import com.mediaLaboSolutionsMicroBack.entity.Patient;
import com.mediaLaboSolutionsMicroBack.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getPatientByIdentity(String nom, String prenom, LocalDate dateNaissance) {
        return patientRepository.findByNomAndPrenomAndDateNaissance(nom, prenom, dateNaissance);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> updatePatient(Long id, Patient updated) {
        return patientRepository.findById(id).map(patient -> {
            patient.setNom(updated.getNom());
            patient.setPrenom(updated.getPrenom());
            patient.setDateNaissance(updated.getDateNaissance());
            patient.setGenre(updated.getGenre());
            patient.setAdresse(updated.getAdresse());
            patient.setTelephone(updated.getTelephone());
            return patientRepository.save(patient);
        });
    }

    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
