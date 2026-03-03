package com.mediaLaboSolutionsMicroBack.repository;

import com.mediaLaboSolutionsMicroBack.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository JPA pour l'entité Genre
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {
    /**
     * Recherche un genre grace à son libelle
     * @param libelle
     * @return Genre
     */
    Genre findByLibelle(String libelle);
}
