package com.mediaLaboSolutionsMicroRisque.entity;

public class PatientDto {

    private Integer id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private GenreDto genre;
    private AdresseDto adresse;
    private TelephoneDto telephone;

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
