package com.mediaLaboSolutionsMicroMongoDB.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Représente une note de MongoDB
 */
@Document(collection = "notes")
public class Note {
    // Identifiant unique de la note
    @Id
    private String id;
    // Identifiant du patient liée à la note
    private Long patId;
    // Nom complet du patient
    private String patient;
    // Contenu de la note
    private String note;

    // Constructeur par défaut
    public Note() {
    }

    // Constructeur complet
    public Note(String id, Long patId, String patient, String note) {
        this.id = id;
        this.patId = patId;
        this.patient = patient;
        this.note = note;
    }

    // ----- Getters et Setters -----

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPatId() {
        return patId;
    }

    public void setPatId(Long patId) {
        this.patId = patId;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
