package com.mediaLaboSolutionsMicroBack.mediaLaboSolutionsMicroBack.service;

import com.mediaLaboSolutionsMicroBack.mediaLaboSolutionsMicroBack.entity.Telephone;
import com.mediaLaboSolutionsMicroBack.mediaLaboSolutionsMicroBack.repository.TelephoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelephoneService {

    private final TelephoneRepository telephoneRepository;

    public TelephoneService(TelephoneRepository telephoneRepository) {
        this.telephoneRepository = telephoneRepository;
    }

    public List<Telephone> getAllTelephones() {
        return telephoneRepository.findAll();
    }

    public Optional<Telephone> getTelephoneById(Long id) {
        return telephoneRepository.findById(id);
    }

    public Telephone getTelephoneByNumero(String numero) {
        return telephoneRepository.findByNumero(numero);
    }

    public Telephone createTelephone(Telephone telephone) {
        return telephoneRepository.save(telephone);
    }

    public Optional<Telephone> updateTelephone(Long id, Telephone updated) {
        return telephoneRepository.findById(id).map(telephone -> {
            telephone.setNumero(updated.getNumero());
            return telephoneRepository.save(telephone);
        });
    }

    public boolean deleteTelephone(Long id) {
        if (telephoneRepository.existsById(id)) {
            telephoneRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
