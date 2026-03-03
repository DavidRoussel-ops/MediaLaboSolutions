package com.mediaLaboSolutionsMicroBack.service;

import com.mediaLaboSolutionsMicroBack.entity.Telephone;
import com.mediaLaboSolutionsMicroBack.repository.TelephoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant la logique métier des téléphones
 */
@Service
public class TelephoneService {

    private final TelephoneRepository telephoneRepository;

    /**
     * Injection du repository Telephone
     * @param telephoneRepository
     */
    public TelephoneService(TelephoneRepository telephoneRepository) {
        this.telephoneRepository = telephoneRepository;
    }

    /**
     * Récupère la liste complète des téléphones
     * @return List<Telephone>
     */
    public List<Telephone> getAllTelephones() {
        return telephoneRepository.findAll();
    }

    /**
     * Récupère un téléphone par son id
     * @param id
     * @return Optional<Telephone>
     */
    public Optional<Telephone> getTelephoneById(Long id) {
        return telephoneRepository.findById(id);
    }

    /**
     * Récupère un téléphone par son numéro
     * @param numero
     * @return Telephone
     */
    public Telephone getTelephoneByNumero(String numero) {
        return telephoneRepository.findByNumero(numero);
    }

    /**
     * Crée un téléphone
     * @param telephone
     * @return Telephone
     */
    public Telephone createTelephone(Telephone telephone) {
        return telephoneRepository.save(telephone);
    }

    /**
     * Met à jour un téléphone
     * @param id
     * @param updated
     * @return Optional<Telephone>
     */
    public Optional<Telephone> updateTelephone(Long id, Telephone updated) {
        return telephoneRepository.findById(id).map(telephone -> {
            telephone.setNumero(updated.getNumero());
            return telephoneRepository.save(telephone);
        });
    }

    /**
     * Supprime un téléphone
     * @param id
     * @return boolean
     */
    public boolean deleteTelephone(Long id) {
        if (telephoneRepository.existsById(id)) {
            telephoneRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
