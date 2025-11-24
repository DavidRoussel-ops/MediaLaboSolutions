package com.mediaLaboSolutionsMicroBack.repository;


import com.mediaLaboSolutionsMicroBack.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Vérification patient via son nom, prenom et date de naissance
    List<Patient> findByNomAndPrenomAndDateNaissance(String nom, String prenom, LocalDate dateNaissance);

    // Recherches de patient par le nom insensible à la casse
    List<Patient> findByNomContainingIgnoreCase(String nom);

    // Recherches de patient par le prenom insensible à la casse
    List<Patient> findByPrenomContainingIgnoreCase(String prenom);
}
