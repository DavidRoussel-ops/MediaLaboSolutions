package com.mediaLaboSolutionsMicroBack.service;

import com.mediaLaboSolutionsMicroBack.entity.Adresse;
import com.mediaLaboSolutionsMicroBack.repository.AdresseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant le logique métier des adresses
 */
@Service
public class AdresseService {

    private final AdresseRepository adresseRepository;

    /**
     * Injection du repository Adresse
     * @param adresseRepository
     */
    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    /**
     * Récupère la liste complête des adresses
     * @return List<Adresse>
     */
    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    /**
     * Récupère une adresse par son id
     * @param id
     * @return Optional<Adresse>
     */
    public Optional<Adresse> getAdresseById(Long id) {
        return adresseRepository.findById(id);
    }

    /**
     * Récupère une adresse par son libelle
     * @param libelle
     * @return Adresse
     */
    public Adresse getAdressesByLibelle(String libelle) {
        return adresseRepository.findByLibelle(libelle);
    }

    /**
     * Crée une adresse
     * @param adresse
     * @return Adresse
     */
    public Adresse createAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    /**
     * Met à jour une adresse
     * @param id
     * @param updated
     * @return Optional<Adresse>
     */
    public Optional<Adresse> updateAdresse(Long id, Adresse updated) {
        return adresseRepository.findById(id).map(adresse -> {
            adresse.setLibelle(updated.getLibelle());
            return adresseRepository.save(adresse);
        });
    }

    /**
     * Supprime une adresse
     * @param id
     * @return boolean
     */
    public boolean deleteAdresse(Long id) {
        if (adresseRepository.existsById(id)) {
            adresseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
