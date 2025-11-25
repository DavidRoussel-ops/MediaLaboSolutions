package com.mediaLaboSolutionsMicroBack.service;

import com.mediaLaboSolutionsMicroBack.entity.Adresse;
import com.mediaLaboSolutionsMicroBack.repository.AdresseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdresseServiceTest {

    private final AdresseRepository adresseRepository = Mockito.mock(AdresseRepository.class);
    private final AdresseService service = new AdresseService(adresseRepository);

    @Test
    public void testGetAllAdresses() {
        List<Adresse> adresses = Arrays.asList(
                new Adresse(1L, "1 rue de la république"),
                new Adresse(2L, "1 rue de la liberté")
        );
        when(adresseRepository.findAll()).thenReturn(adresses);

        List<Adresse> result = service.getAllAdresses();

        assertEquals(2, result.size());
        assertEquals("1 rue de la liberté", result.get(1).getLibelle());
    }

    @Test
    public void testGetAllAdressesNotFound() {
        when(adresseRepository.findAll()).thenReturn(Collections.emptyList());

        List<Adresse> result = service.getAllAdresses();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAdresseById() {
        Adresse adresse = new Adresse(1L, "1 rue de la liberté");
        when(adresseRepository.findById(1L)).thenReturn(Optional.of(adresse));

        Optional<Adresse> result = service.getAdresseById(1L);

        assertTrue(result.isPresent());
        assertEquals("1 rue de la liberté", result.get().getLibelle());
    }

    @Test
    public void testGetAdresseByIdNotFound() {
        when(adresseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Adresse> result = service.getAdresseById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetAdresseByLibelle() {
        Adresse adresse = new Adresse(1L, "1 rue de la liberté");
        when(adresseRepository.findByLibelle("1 rue de la liberté")).thenReturn(adresse);

        Adresse result = service.getAdressesByLibelle("1 rue de la liberté");

        assertNotNull(result);
        assertEquals("1 rue de la liberté", result.getLibelle());
    }

    @Test
    public void testGetAdresseByLibelleNotFound() {
        when(adresseRepository.findByLibelle("1 rue de la liberté")).thenReturn(null);

        Adresse result = service.getAdressesByLibelle("1 rue de la liberté");

        assertNull(result);
    }

    @Test
    public void testCreateAdresse() {
        Adresse adresse = new Adresse(null, "1 rue de la liberté");
        Adresse saved = new Adresse(1L, "1 rue de la liberté");
        when(adresseRepository.save(adresse)).thenReturn(saved);

        Adresse result = service.createAdresse(adresse);

        assertNotNull(result.getId());
        assertEquals("1 rue de la liberté", result.getLibelle());
    }

    @Test
    public void testUpdateAdresse() {
        Adresse existing = new Adresse(1L, "1 rue de la liberté");
        Adresse updated = new Adresse(null, "1 rue de la liberté du 1er Juin");

        when(adresseRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(adresseRepository.save(existing)).thenReturn(existing);

        Optional<Adresse> result = service.updateAdresse(1L, updated);

        assertTrue(result.isPresent());
        assertEquals("1 rue de la liberté du 1er Juin", result.get().getLibelle());
    }

    @Test
    public void testUpdateAdresseNotFound() {
        Adresse updated = new Adresse(null, "1 rue de la liberté du 1er Juin");

        when(adresseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Adresse> result = service.updateAdresse(1L, updated);

        assertFalse(result.isPresent());
    }

    @Test
    public void testDeleteAdresse() {
        when(adresseRepository.existsById(1L)).thenReturn(true);
        doNothing().when(adresseRepository).deleteById(1L);

        boolean result = service.deleteAdresse(1L);

        assertTrue(result);
        verify(adresseRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAdresseNotFound() {
        when(adresseRepository.existsById(1L)).thenReturn(false);

        boolean result = service.deleteAdresse(1L);

        assertFalse(result);
        verify(adresseRepository, never()).deleteById(anyLong());
    }
}
