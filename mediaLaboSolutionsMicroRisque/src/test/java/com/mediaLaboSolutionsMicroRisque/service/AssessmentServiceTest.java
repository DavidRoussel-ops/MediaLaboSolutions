package com.mediaLaboSolutionsMicroRisque.service;

import com.mediaLaboSolutionsMicroRisque.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class AssessmentServiceTest {

    @Mock
    private PatientClient patientClient;

    @Mock
    private NotesClient notesClient;

    @Mock
    private AssessmentService assessmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        assessmentService = new AssessmentService(patientClient, notesClient);
    }

    @Test
    public void calculateAgeCorrectly() {
        String dob = "2000-01-01";
        int age = assessmentService.calculateAge(dob);
        assertThat(age).isGreaterThan(20);
    }

    @Test
    public void returnZeroWhenNull() {
        int age = assessmentService.calculateAge(null);
        assertThat(age).isZero();
    }

    @Test
    public void returnZeroWhenNoTriggers() {
        List<NoteDto> noteDtos = List.of(
                new NoteDto("1", 1, "test", "Le patient va bien."),
                new NoteDto("2", 1, "test", "Tout vas toujours bien.")
        );
        int count = assessmentService.countTriggers(noteDtos);
        assertThat(count).isZero();
    }

    @Test
    public void countOneTrigger() {
        List<NoteDto> noteDtos = List.of(
                new NoteDto("1", 1, "test", "Le patient à un taux élevé de Cholestérol.")
        );
        int count = assessmentService.countTriggers(noteDtos);
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void countMultipleTriggers() {
        List<NoteDto> noteDtos = List.of(
                new NoteDto("1", 1, "test", "Hémoglobine A1C élevée."),
                new NoteDto("2", 1, "test", "Le patient est Fumeur et présente une Microalbumine.")
        );
        int count = assessmentService.countTriggers(noteDtos);
        assertThat(count).isEqualTo(3);
    }

    @Test
    public void countMultipleTriggersIgnoringCase() {
        List<NoteDto> noteDtos = List.of(
                new NoteDto("1", 1, "test", "La patiente est fumeuse et precise qu'elle à une consommation ANORMAL en ce moment.")
        );
        int count = assessmentService.countTriggers(noteDtos);
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void returnNone() {
        RiskLevel level = assessmentService.computeRiskLevel(45, "M", 0);
        assertThat(level).isEqualTo(RiskLevel.NONE);
    }

    @Test
    public void returnBorderline() {
        RiskLevel level = assessmentService.computeRiskLevel(50, "F", 3);
        assertThat(level).isEqualTo(RiskLevel.BORDERLINE);
    }

    @Test
    public void returnInDanger() {
        RiskLevel level = assessmentService.computeRiskLevel(60, "M", 6);
        assertThat(level).isEqualTo(RiskLevel.IN_DANGER);
    }

    @Test
    public void returnEarlyOnset() {
        RiskLevel level = assessmentService.computeRiskLevel(40, "F", 10);
        assertThat(level).isEqualTo(RiskLevel.EARLY_ONSET);
    }

    @Test
    public void returnInDangerMale() {
        RiskLevel level = assessmentService.computeRiskLevel(25, "M", 3);
        assertThat(level).isEqualTo(RiskLevel.IN_DANGER);
    }

    @Test
    public void returnEarlyOnsetMale() {
        RiskLevel level = assessmentService.computeRiskLevel(25, "M", 5);
        assertThat(level).isEqualTo(RiskLevel.EARLY_ONSET);
    }

    @Test
    public void returnInDangerFemale() {
        RiskLevel level = assessmentService.computeRiskLevel(25, "F", 4);
        assertThat(level).isEqualTo(RiskLevel.IN_DANGER);
    }

    @Test
    public void returnEarlyOnsetFemale() {
        RiskLevel level = assessmentService.computeRiskLevel(25, "F", 7);
        assertThat(level).isEqualTo(RiskLevel.EARLY_ONSET);
    }

    @Test
    public void returnCompleteAssessmentResult() {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(1);
        patientDto.setNom("Dupont");
        patientDto.setPrenom("Michel");
        patientDto.setDateNaissance("1980-01-01");

        GenreDto genreDto = new GenreDto();
        genreDto.setId(1);
        genreDto.setLibelle("M");
        patientDto.setGenre(genreDto);

        NoteDto noteDto1 = new NoteDto("Note1", 1, "Michel Dupont", "Hémoglobine A1C élevée");
        NoteDto noteDto2 = new NoteDto("Note2", 1, "Michel Dupont", "Le patient est Fumeur");

        List<NoteDto> noteDtos = List.of(noteDto1, noteDto2);

        when(patientClient.getPatientById(1)).thenReturn(patientDto);
        when(notesClient.getNotesByPatient(1)).thenReturn(noteDtos);

        AssessmentResult result = assessmentService.assessmentResult(1);

        assertThat(result.getPatientId()).isEqualTo(1);
        assertThat(result.getPatientName()).isEqualTo("Michel Dupont");
        assertThat(result.getAge()).isGreaterThan(40);
        assertThat(result.getGender()).isEqualTo("M");
        assertThat(result.getTriggerCount()).isEqualTo(2);
        assertThat(result.getRiskLevel()).isEqualTo(RiskLevel.BORDERLINE);
        assertThat(result.getMessage()).contains("Michel Dupont");
        assertThat(result.getMessage()).contains("risque: BORDERLINE");
    }
}
