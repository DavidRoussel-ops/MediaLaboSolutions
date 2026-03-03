package com.mediaLaboSolutionsMicroBack.entity;

import jakarta.persistence.*;

/**
 * Entité représentant le genre
 */
@Entity
public class Genre {
    // Identifant unique du genre
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Libelle du genre
    @Column(nullable = false, unique = true)
    private String libelle;

    // Constructeur par défaut
    public Genre(){}

    // Constructeur complet
    public Genre(Long id, String libelle) {
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
