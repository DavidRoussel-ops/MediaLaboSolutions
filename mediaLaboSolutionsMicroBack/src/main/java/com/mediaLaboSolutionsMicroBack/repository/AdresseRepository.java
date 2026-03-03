package com.mediaLaboSolutionsMicroBack.repository;

import com.mediaLaboSolutionsMicroBack.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository JPA pour l'entité Adresse
 */
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
    /**
     * Recherche une adresse grace à son libelle
     * @param libelle
     * @return Adresse
     */
    Adresse findByLibelle(String libelle);
}
