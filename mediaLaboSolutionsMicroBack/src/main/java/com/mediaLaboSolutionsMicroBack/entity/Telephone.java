package com.mediaLaboSolutionsMicroBack.entity;

import jakarta.persistence.*;

/**
 * Entité représentant le téléphone
 */
@Entity
public class Telephone {
    // Indentifiant unique du téléphone
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Numéro du téléphone
    private String numero;

    // Constructeur par défaut
    public Telephone(){}

    // Constructeur complet
    public Telephone(Long id, String numero) {
        this.id = id;
        this.numero = numero;
    }

    // ----- Getters et Setters -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
