package com.mediaLaboSolutionsMicroBack.controller;

import com.mediaLaboSolutionsMicroBack.entity.Telephone;
import com.mediaLaboSolutionsMicroBack.service.TelephoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour le CRUD sur les téléphones
 */
@RestController
@RequestMapping("/api/telephones")
public class TelephoneController {

    private final TelephoneService telephoneService;

    /**
     * Constructeur telephoneService
     * @param telephoneService
     */
    public TelephoneController(TelephoneService telephoneService) {
        this.telephoneService = telephoneService;
    }

    /**
     * Récupère la liste des téléphones
     * @return List<Telephone>
     */
    @GetMapping
    public List<Telephone> getAllTelephones() {
        return telephoneService.getAllTelephones();
    }

    /**
     * Récupère un téléphone par son id
     * @param id
     * @return ResponseEntity<Telephone>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Telephone> getTelephoneById(@PathVariable Long id) {
        return telephoneService.getTelephoneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée une nouveau téléphone
     * @param telephone
     * @return ResponseEntity<Telephone>
     */
    @PostMapping
    public ResponseEntity<Telephone> createTelephone(@RequestBody Telephone telephone) {
            Telephone saved = telephoneService.createTelephone(telephone);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Met à jour un téléphone
     * @param id
     * @param updated
     * @return ResponseEntity<Telephone>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Telephone> updateTelephone(@PathVariable Long id, @RequestBody Telephone updated) {
        return telephoneService.updateTelephone(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime un téléphone
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelephone(@PathVariable Long id) {
        return telephoneService.deleteTelephone(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
