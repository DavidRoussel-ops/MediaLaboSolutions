package com.example.mediaLaboSolutionsMicroBack.repository;

import com.example.mediaLaboSolutionsMicroBack.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    //Permet de retourner le numero de téléphone du patient
    Telephone findByNumero(String numero);
}
