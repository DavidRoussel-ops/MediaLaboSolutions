package com.mediaLaboSolutionsMicroRisque.service;

import com.mediaLaboSolutionsMicroRisque.entity.AssessmentResult;
import com.mediaLaboSolutionsMicroRisque.entity.NoteDto;
import com.mediaLaboSolutionsMicroRisque.entity.PatientDto;
import com.mediaLaboSolutionsMicroRisque.entity.RiskLevel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Service charger de calculé le niveau de risque d'un patient
 */
@Service
public class AssessmentService {

    private final PatientClient patientClient;
    private final NotesClient notesClient;

    /**
     * Injection des client REST pour les patients et notes
     * @param patientClient
     * @param notesClient
     */
    public AssessmentService(PatientClient patientClient, NotesClient notesClient) {
        this.patientClient = patientClient;
        this.notesClient = notesClient;
    }

    /**
     * Calcule le résultat de l'évaluation du risque pour un patient
     * @param patientId
     * @return AssessmentResult
     */
    public AssessmentResult assessmentResult(Integer patientId) {
        PatientDto patientDto = patientClient.getPatientById(patientId);
        List<NoteDto> noteDtos = notesClient.getNotesByPatient(patientId);

        int age = calculateAge(patientDto.getDateNaissance());
        int triggerCount = countTriggers(noteDtos);

        RiskLevel level = computeRiskLevel(age, patientDto.getGenre().getLibelle(), triggerCount);

        AssessmentResult result = new AssessmentResult();
        result.setPatientId(patientId);
        result.setPatientName(patientDto.getPrenom() + " " + patientDto.getNom());
        result.setAge(age);
        result.setGender(patientDto.getGenre().getLibelle());
        result.setTriggerCount(triggerCount);
        result.setRiskLevel(level);
        result.setMessage("Patient: " + result.getPatientName() + " (" + age + " ans) risque: " + level);

        return result;
    }

    /**
     * Calcule l'age d'un patient
     * @param dob
     * @return int
     */
    int calculateAge(String dob) {
        if (dob == null || dob.isBlank()) return 0;
        return Period.between(LocalDate.parse(dob), LocalDate.now()).getYears();
    }

    /**
     * Compte le nombre de déclencheurs présents dans les notes
     * @param noteDtos
     * @return int
     */
    int countTriggers(List<NoteDto> noteDtos) {
        String[] triggers = {
                "Hémoglobine A1C", "Microalbumine", "Taille", "Poids",
                "Fumeur", "Fumeuse", "Anormal", "Cholestérol", "Vertiges",
                "Rechute", "Réaction", "Anticorps"
        };

        int count = 0;
        for (NoteDto noteDto : noteDtos) {
            String text = noteDto.getNote().toLowerCase();
            for (String trigger : triggers) {
                if (text.contains(trigger.toLowerCase())) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Détermine le niveau de rsique selon l'age, le genre et le nombre de déclencheur
     * @param age
     * @param gender
     * @param triggers
     * @return RiskLevel
     */
    RiskLevel computeRiskLevel(int age, String gender, int triggers) {
        if (triggers == 0) return RiskLevel.NONE;

        if (age > 30) {
            if (triggers >= 8) return RiskLevel.EARLY_ONSET;
            if (triggers >= 6) return RiskLevel.IN_DANGER;
            if (triggers >= 2 && triggers <= 5) return RiskLevel.BORDERLINE;
        } else {
            boolean male = gender.equalsIgnoreCase("M");
            if (male) {
                if (triggers >= 5) return RiskLevel.EARLY_ONSET;
                if (triggers >= 3) return RiskLevel.IN_DANGER;
            } else {
                if (triggers >= 7) return RiskLevel.EARLY_ONSET;
                if (triggers >= 4) return RiskLevel.IN_DANGER;
            }
        }
        return RiskLevel.NONE;
    }
}
