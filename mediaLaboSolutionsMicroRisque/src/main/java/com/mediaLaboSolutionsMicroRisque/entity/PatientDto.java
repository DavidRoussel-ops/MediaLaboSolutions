package com.mediaLaboSolutionsMicroRisque.entity;

/**
 * Représente un Patient utiliser comme DTO
 */
public class PatientDto {

    // Identifiant unique d'un Patient
    private Integer id;
    // Nom d'un patient
    private String nom;
    // Prénom d'un patient
    private String prenom;
    // Date de naissance d'un patient
    private String dateNaissance;
    // Genre du patient
    private GenreDto genre;
    // Adresse d'un patient
    private AdresseDto adresse;
    // Téléphone d'un patient
    private TelephoneDto telephone;

    // ----- Getters et Setters -----

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public GenreDto getGenre() {
        return genre;
    }

    public void setGenre(GenreDto genre) {
        this.genre = genre;
    }

    public AdresseDto getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseDto adresse) {
        this.adresse = adresse;
    }

    public TelephoneDto getTelephone() {
        return telephone;
    }

    public void setTelephone(TelephoneDto telephone) {
        this.telephone = telephone;
    }
}
