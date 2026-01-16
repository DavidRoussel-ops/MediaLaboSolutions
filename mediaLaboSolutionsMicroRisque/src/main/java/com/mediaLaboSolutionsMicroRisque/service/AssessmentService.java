package com.mediaLaboSolutionsMicroRisque.service;

import com.mediaLaboSolutionsMicroRisque.entity.AssessmentResult;
import com.mediaLaboSolutionsMicroRisque.entity.NoteDto;
import com.mediaLaboSolutionsMicroRisque.entity.PatientDto;
import com.mediaLaboSolutionsMicroRisque.entity.RiskLevel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AssessmentService {

    private final PatientClient patientClient;
    private final NotesClient notesClient;

    public AssessmentService(PatientClient patientClient, NotesClient notesClient) {
        this.patientClient = patientClient;
        this.notesClient = notesClient;
    }

    public AssessmentResult assessmentResult(Integer patientId) {
        PatientDto patientDto = patientClient.getPatientById(patientId);
        List<NoteDto> noteDtos = notesClient.getNotesByPatient(patientId);

        int age = calculateAge(patientDto.getDob());
        int triggerCount = countTriggers(noteDtos);

        RiskLevel level = computeRiskLevel(age, patientDto.getGender(), triggerCount);

        AssessmentResult result = new AssessmentResult();
        result.setPatientId(patientId);
        result.setPatientName(patientDto.getGiven() + " " + patientDto.getFamily());
        result.setAge(age);
        result.setGender(patientDto.getGender());
        result.setTriggerCount(triggerCount);
        result.setRiskLevel(level);
        result.setMessage("Patient: " + result.getPatientName() + " (" + age + " ans) risque: " + level);

        return result;
    }

    private int calculateAge(String dob) {
        return Period.between(LocalDate.parse(dob), LocalDate.now()).getYears();
    }

    private int countTriggers(List<NoteDto> noteDtos) {
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

    private RiskLevel computeRiskLevel(int age, String gender, int triggers) {
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
