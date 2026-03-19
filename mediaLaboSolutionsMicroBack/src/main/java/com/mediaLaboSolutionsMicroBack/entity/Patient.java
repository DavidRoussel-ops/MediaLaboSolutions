package com.mediaLaboSolutionsMicroBack.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entité représentant un patient
 */
@Entity
public class Patient {
    // Identifiant unique du patient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Nom du patient
    @Column(nullable = false)
    private String nom;
    // Prénom du patient
    @Column(nullable = false)
    private String prenom;
    // Date de naissance du patient
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    // Genre du patient
    @ManyToOne(optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    // Adresse du patient
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    // Téléphone du patient
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "telephone_id")
    private Telephone telephone;

    // Constructeur par défaut
    public Patient() {}

    // Constructeur complet
    public Patient(Long id, String nom, String prenom, LocalDate dateNaissance, Genre genre, Adresse adresse, Telephone telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    // ----- Getters et Setters -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }
}
