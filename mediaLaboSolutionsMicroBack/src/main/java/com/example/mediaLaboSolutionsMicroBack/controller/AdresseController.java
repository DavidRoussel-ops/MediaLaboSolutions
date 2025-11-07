package com.example.mediaLaboSolutionsMicroBack.controller;

import com.example.mediaLaboSolutionsMicroBack.entity.Adresse;
import com.example.mediaLaboSolutionsMicroBack.service.AdresseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adresses")
public class AdresseController {

    private final AdresseService adresseService;

    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    @GetMapping
    public List<Adresse> getAllAdresses() {
        return adresseService.getAllAdresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable Long id) {
        return adresseService.getAdresseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Adresse createAdresse(@RequestBody Adresse adresse) {
        return adresseService.createAdresse(adresse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable Long id, @RequestBody Adresse updated) {
        return adresseService.updateAdresse(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        return adresseService.deleteAdresse(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
