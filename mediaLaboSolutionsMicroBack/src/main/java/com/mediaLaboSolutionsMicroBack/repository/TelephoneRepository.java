package com.mediaLaboSolutionsMicroBack.repository;

import com.mediaLaboSolutionsMicroBack.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository JPA pour l'entité Telephone
 */
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    /**
     * Recherche un téléphone grace à son numéro
     * @param numero
     * @return Telephone
     */
    Telephone findByNumero(String numero);
}
