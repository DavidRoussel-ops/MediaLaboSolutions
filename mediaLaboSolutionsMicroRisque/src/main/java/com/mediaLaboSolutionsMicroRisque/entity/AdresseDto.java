package com.mediaLaboSolutionsMicroRisque.entity;

/**
 * Représente une adresse utiliser comme DTO
 */
public class AdresseDto {

    // Identifaint unique de l'adresse
    private Integer id;
    // Libelle de l'adresse
    private String libelle;

    // ----- Getters et Setters -----

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
