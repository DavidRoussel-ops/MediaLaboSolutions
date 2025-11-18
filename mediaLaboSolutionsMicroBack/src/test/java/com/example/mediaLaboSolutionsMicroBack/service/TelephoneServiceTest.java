package com.example.mediaLaboSolutionsMicroBack.service;

import com.example.mediaLaboSolutionsMicroBack.entity.Telephone;
import com.example.mediaLaboSolutionsMicroBack.repository.TelephoneRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TelephoneServiceTest {

    private final TelephoneRepository telephoneRepository = Mockito.mock(TelephoneRepository.class);
    private final TelephoneService service = new TelephoneService(telephoneRepository);

    @Test
    public void testGetTelephones() {
        List<Telephone> telephones = Arrays.asList(
                new Telephone(1L, "123-555-6789"),
                new Telephone(2L, "456-555-1234")
        );
        when(telephoneRepository.findAll()).thenReturn(telephones);

        List<Telephone> result = service.getAllTelephones();

        assertEquals(2, result.size());
        assertEquals("123-555-6789", result.get(0).getNumero());
    }

    @Test
    public void testGetTelephonesNotFound() {
        when(telephoneRepository.findAll()).thenReturn(Collections.emptyList());

        List<Telephone> result = service.getAllTelephones();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetTelephoneById() {
        Telephone telephone = new Telephone(1L, "123-555-6789");
        when(telephoneRepository.findById(1L)).thenReturn(Optional.of(telephone));

        Optional<Telephone> result = service.getTelephoneById(1L);

        assertTrue(result.isPresent());
        assertEquals("123-555-6789", result.get().getNumero());
    }

    @Test
    public void testGetTelephoneByIdNotFound() {
        when(telephoneRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Telephone> result = service.getTelephoneById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetTelephoneByNumero() {
        Telephone telephone = new Telephone(1L, "123-555-6789");
        when(telephoneRepository.findByNumero("123-555-6789")).thenReturn(telephone);

        Telephone result = service.getTelephoneByNumero("123-555-6789");

        assertNotNull(result);
        assertEquals("123-555-6789", result.getNumero());
    }

    @Test
    public void testGetTelephoneByNumeroNotFound() {
        when(telephoneRepository.findByNumero("123-555-6789")).thenReturn(null);

        Telephone result = service.getTelephoneByNumero("123-555-6789");

        assertNull(result);
    }

    @Test
    public void testCreateTelephone() {
        Telephone telephone = new Telephone(null, "123-555-6789");
        Telephone saved = new Telephone(1L, "123-555-6789");
        when(telephoneRepository.save(telephone)).thenReturn(saved);

        Telephone result = service.createTelephone(telephone);

        assertNotNull(result.getId());
        assertEquals("123-555-6789", result.getNumero());
    }

    @Test
    public void testUpdateTelephone() {
        Telephone existing = new Telephone(1L, "123-555-6789");
        Telephone updated = new Telephone(null, "456-555-1238");

        when(telephoneRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(telephoneRepository.save(existing)).thenReturn(existing);

        Optional<Telephone> result = service.updateTelephone(1L, updated);

        assertTrue(result.isPresent());
        assertEquals("456-555-1238", result.get().getNumero());
    }

    @Test
    public void testUpdateTelephoneNotFound() {
        Telephone updated = new Telephone(null, "456-555-1238");

        when(telephoneRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Telephone> result = service.updateTelephone(1L, updated);

        assertFalse(result.isPresent());
    }

    @Test
    public void testDeleteTelephone() {
        when(telephoneRepository.existsById(1L)).thenReturn(true);
        doNothing().when(telephoneRepository).deleteById(1L);

        boolean result = service.deleteTelephone(1L);

        assertTrue(result);
        verify(telephoneRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteTelephoneNotFound() {
        when(telephoneRepository.existsById(1L)).thenReturn(false);

        boolean result = service.deleteTelephone(1L);

        assertFalse(result);
        verify(telephoneRepository, never()).deleteById(anyLong());
    }
}
