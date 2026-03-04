package com.mediaLaboSolutionsMicroRisque.entity;

/**
 * Représente le résultat complet de l'évaluation du risque médical d'un patient
 */
public class AssessmentResult {

    // Identifiant du patient évalué
    private Integer patientId;
    // Nom complet d'un patient
    private String patientName;
    // Age du patient
    private int age;
    // Genre du patient
    private String gender;
    // Nombre de déclancheurs détectés
    private int triggerCount;
    // Niveau de risque
    private RiskLevel riskLevel;
    // Message complet explicatif
    private String message;

    // ----- Getters et Setters -----

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getTriggerCount() {
        return triggerCount;
    }

    public void setTriggerCount(int triggerCount) {
        this.triggerCount = triggerCount;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
