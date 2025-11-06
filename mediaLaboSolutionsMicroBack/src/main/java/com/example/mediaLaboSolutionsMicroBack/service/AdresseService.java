package com.example.mediaLaboSolutionsMicroBack.service;

import com.example.mediaLaboSolutionsMicroBack.entity.Adresse;
import com.example.mediaLaboSolutionsMicroBack.repository.AdresseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    private final AdresseRepository adresseRepository;

    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    public Optional<Adresse> getAdresseById(Long id) {
        return adresseRepository.findById(id);
    }

    public List<Adresse> getAdressesByLibelle(String libelle) {
        return adresseRepository.findByLibelle(libelle);
    }

    public Adresse createAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    public Optional<Adresse> updateAdresse(Long id, Adresse updated) {
        return adresseRepository.findById(id).map(adresse -> {
            adresse.setLibelle(updated.getLibelle());
            return adresseRepository.save(adresse);
        });
    }

    public boolean deleteAdresse(Long id) {
        if (adresseRepository.existsById(id)) {
            adresseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
