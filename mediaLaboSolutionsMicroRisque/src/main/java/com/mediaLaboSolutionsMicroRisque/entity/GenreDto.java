package com.mediaLaboSolutionsMicroRisque.entity;

/**
 * Représente un Genre utiliser comme DTO
 */
public class GenreDto {

    // Identifiant unique du Genre
    private Integer id;
    // Libelle du genre
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
