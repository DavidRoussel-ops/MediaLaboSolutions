package com.mediaLaboSolutionsMicroBack.controller;

import com.mediaLaboSolutionsMicroBack.entity.Adresse;
import com.mediaLaboSolutionsMicroBack.service.AdresseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour le CRUD sur les adresses.
 */
@RestController
@RequestMapping("/api/adresses")
public class AdresseController {

    private final AdresseService adresseService;

    /**
     * Constructeur adresseService
     * @param adresseService
     */
    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    /**
     * Récupère la liste des adresses
     * @return List<Adresse>
     */
    @GetMapping
    public List<Adresse> getAllAdresses() {
        return adresseService.getAllAdresses();
    }

    /**
     * Récupère une adresse par son id
     * @param id
     * @return ResponseEntity<Adresse>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable Long id) {
        return adresseService.getAdresseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée une nouelle adresse
     * @param adresse
     * @return ResponseEntity<Adresse>
     */
    @PostMapping
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse) {
        Adresse saved = adresseService.createAdresse(adresse);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Met à jour une adresse
     * @param id
     * @param updated
     * @return ResponseEntity<Adresse>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable Long id, @RequestBody Adresse updated) {
        return adresseService.updateAdresse(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime une adresse
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        return adresseService.deleteAdresse(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
