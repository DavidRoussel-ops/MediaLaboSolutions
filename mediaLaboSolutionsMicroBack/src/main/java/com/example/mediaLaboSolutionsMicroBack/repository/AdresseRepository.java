package com.example.mediaLaboSolutionsMicroBack.repository;

import com.example.mediaLaboSolutionsMicroBack.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {
    //Permet de retrouver un patient via le nom de sa ville
    List<Adresse> findByVille(String ville);
    //Permet de retrouver un patient via le code postal sa ville
    List<Adresse> findByCodePostal(String codePostal);
}
