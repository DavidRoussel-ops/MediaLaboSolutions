package com.mediaLaboSolutionsMicroBack.service;

import com.mediaLaboSolutionsMicroBack.entity.Genre;
import com.mediaLaboSolutionsMicroBack.entity.Patient;
import com.mediaLaboSolutionsMicroBack.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    private final PatientRepository patientRepository = Mockito.mock(PatientRepository.class);
    private final PatientService service = new PatientService(patientRepository);

    @Test
    public void testGetAllPatients() {
        List<Patient> patients = Arrays.asList(
                new Patient(1L, "Dupont", "Jean", LocalDate.of(1990,1,1), new Genre(1L,"Homme"), null, null),
                new Patient(2L, "Dupont", "Michel", LocalDate.of(1995,5,5), new Genre(1L,"Homme"), null, null)
        );
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = service.getAllPatients();

        assertEquals(2, result.size());
        assertEquals("Jean", result.get(0).getPrenom());
    }

    @Test
    public void testGetPatientById() {
        Patient patient = new Patient(1L, "Dupont", "Jean", LocalDate.of(1990,1,1), new Genre(1L,"Homme"), null, null);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Optional<Patient> result = service.getPatientById(1L);

        assertTrue(result.isPresent());
        assertEquals("Dupont", result.get().getNom());
    }

    @Test
    public void testGetPatientByIdNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Patient> result = service.getPatientById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetPatientByIdentity() {
        LocalDate birthdate = LocalDate.of(1990,1,1);
        Patient patient = new Patient(1L, "Dupont", "Jean", birthdate, new Genre(1L,"Homme"), null, null);
        when(patientRepository.findByNomAndPrenomAndDateNaissance("Dupont", "Jean", birthdate))
                .thenReturn(Collections.singletonList(patient));

        List<Patient> result = service.getPatientByIdentity("Dupont", "Jean", birthdate);

        assertEquals(1, result.size());
        assertEquals("Dupont", result.get(0).getNom());
    }

    @Test
    public void testGetPatientByIdentityNotFound() {
        LocalDate birthdate = LocalDate.of(1990,1,1);
        when(patientRepository.findByNomAndPrenomAndDateNaissance("Dupont", "Jean", birthdate))
                .thenReturn(Collections.emptyList());

        List<Patient> result = service.getPatientByIdentity("Dupont", "Jean", birthdate);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient(null, "Dupont", "Jean", LocalDate.of(1990,1,1), new Genre(1L,"Homme"), null, null);
        when(patientRepository.save(patient)).thenReturn(new Patient(1L, "Dupont", "Jean", LocalDate.of(1990,1,1), patient.getGenre(), null, null));

        Patient saved = service.createPatient(patient);

        assertNotNull(saved.getId());
        assertEquals("Dupont", saved.getNom());
    }

    @Test
    public void testUpdatePatient() {
        LocalDate dateExpected = LocalDate.of(1995,5,5);
        Patient existing = new Patient(1L, "Dupont", "Jean", LocalDate.of(1990,1,1), new Genre(1L,"Homme"), null, null);
        Patient updated =  new Patient(null, "Dupont", "Michel", LocalDate.of(1995,5,5), new Genre(1L,"Homme"), null, null);

        when(patientRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(patientRepository.save(existing)).thenReturn(existing);

        Optional<Patient> result = service.updatePatient(1L, updated);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Michel", result.get().getPrenom());
        assertEquals(dateExpected, result.get().getDateNaissance());
    }

    @Test
    public void testUpdatePatientNotFound() {
        Patient updated =  new Patient(null, "Dupont", "Michel", LocalDate.of(1995,5,5), new Genre(1L,"Homme"), null, null);

        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Patient> result = service.updatePatient(1L, updated);

        assertFalse(result.isPresent());
    }

    @Test
    public void testDeletePatient() {
        when(patientRepository.existsById(1L)).thenReturn(true);
        doNothing().when(patientRepository).deleteById(1L);

        boolean result = service.deletePatient(1L);

        assertTrue(result);
        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletePatientNotFound() {
        when(patientRepository.existsById(1L)).thenReturn(false);

        boolean result = service.deletePatient(1L);

        assertFalse(result);
        verify(patientRepository, never()).deleteById(anyLong());
    }
}
