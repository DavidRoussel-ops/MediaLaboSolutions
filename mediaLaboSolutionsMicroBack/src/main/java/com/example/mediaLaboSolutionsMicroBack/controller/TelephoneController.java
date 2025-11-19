package com.example.mediaLaboSolutionsMicroBack.controller;

import com.example.mediaLaboSolutionsMicroBack.entity.Telephone;
import com.example.mediaLaboSolutionsMicroBack.service.TelephoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telephones")
public class TelephoneController {

    private final TelephoneService telephoneService;

    public TelephoneController(TelephoneService telephoneService) {
        this.telephoneService = telephoneService;
    }

    @GetMapping
    public List<Telephone> getAllTelephones() {
        return telephoneService.getAllTelephones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telephone> getTelephoneById(@PathVariable Long id) {
        return telephoneService.getTelephoneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Telephone> createTelephone(@RequestBody Telephone telephone) {
            Telephone saved = telephoneService.createTelephone(telephone);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Telephone> updateTelephone(@PathVariable Long id, @RequestBody Telephone updated) {
        return telephoneService.updateTelephone(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelephone(@PathVariable Long id) {
        return telephoneService.deleteTelephone(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
