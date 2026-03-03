package com.mediaLaboSolutionsMicroBack.repository;


import com.mediaLaboSolutionsMicroBack.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository JPA pour l'entité Patient
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
    /**
     * Recherche un ou plusieurs patients par leur noms, prénoms et date de naissances
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @return List<Patient>
     */
    List<Patient> findByNomAndPrenomAndDateNaissance(String nom, String prenom, LocalDate dateNaissance);

    /**
     * Recherche un patient dont le nom contient une chaine de caractères sans tenir compte de la casse.
     * @param nom
     * @return List<Patient>
     */
    List<Patient> findByNomContainingIgnoreCase(String nom);

    /**
     * Recherche un patient dont le prénom contient une chaine de caractères sans tenir compte de la casse.
     * @param prenom
     * @return List<Patient>
     */
    List<Patient> findByPrenomContainingIgnoreCase(String prenom);
}
