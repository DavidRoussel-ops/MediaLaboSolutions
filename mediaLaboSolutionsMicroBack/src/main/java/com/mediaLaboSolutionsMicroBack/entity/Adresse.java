package com.mediaLaboSolutionsMicroBack.entity;

import jakarta.persistence.*;

/**
 * Entité représentant une adresse
 */
@Entity
public class Adresse {
    // Identifiant unique de l'adresse
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Libelle de l'adresse
    private String libelle;

    // Constructeur par défaut
    public Adresse(){}

    // Constructeur complet
    public Adresse(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    // ----- Getters et Setters -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
