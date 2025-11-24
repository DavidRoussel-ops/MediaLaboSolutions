package com.mediaLaboSolutionsMicroBack.repository;

import com.mediaLaboSolutionsMicroBack.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {
    //Permet de retrouver un patient via le nom de sa ville
    Adresse findByLibelle(String libelle);
}
