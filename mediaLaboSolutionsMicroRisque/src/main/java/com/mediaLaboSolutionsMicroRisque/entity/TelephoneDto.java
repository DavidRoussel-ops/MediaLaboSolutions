package com.mediaLaboSolutionsMicroRisque.entity;

/**
 * Représente un Telephone utiliser comme DTO
 */
public class TelephoneDto {

    // Identifiant unique du téléphone
    private Integer id;
    // Numéro du téléphone
    private String numero;

    // ----- Getters et Setters -----

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
