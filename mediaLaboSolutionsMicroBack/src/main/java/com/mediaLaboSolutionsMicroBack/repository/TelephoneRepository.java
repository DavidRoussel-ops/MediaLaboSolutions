package com.mediaLaboSolutionsMicroBack.mediaLaboSolutionsMicroBack.repository;

import com.mediaLaboSolutionsMicroBack.mediaLaboSolutionsMicroBack.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    //Permet de retourner le numero de téléphone du patient
    Telephone findByNumero(String numero);
}
