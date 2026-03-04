package com.mediaLaboSolutionsMicroRisque.entity;

/**
 * Représente une Note utiliser comme DTO
 */
public class NoteDto {

    // Identifiant unique de la note
    private String id;
    // Identifiant du patient reliée à la note
    private Integer patId;
    // Nom complet du patient
    private String patient;
    // Contenu de la note
    private String note;

    /**
     * Contructeur complet
     * @param id
     * @param patId
     * @param patient
     * @param note
     */
    public NoteDto(String id, Integer patId, String patient, String note) {
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

    public Integer getPatId() {
        return patId;
    }

    public void setPatId(Integer patId) {
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
