package com.mediaLaboSolutionsMicroBack.mediaLaboSolutionsMicroBack.repository;

import com.mediaLaboSolutionsMicroBack.mediaLaboSolutionsMicroBack.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    //Permet de retrouver un patient via le libelle de son genre M ou F
    Genre findByLibelle(String libelle);
}
