package com.mediaLaboSolutionsMicroBack.service;

import com.mediaLaboSolutionsMicroBack.entity.Genre;
import com.mediaLaboSolutionsMicroBack.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GenreServiceTest {

    private final GenreRepository genreRepository = Mockito.mock(GenreRepository.class);
    private final GenreService service = new GenreService(genreRepository);

    @Test
    public void testGetAllGenres() {
        List<Genre> genres = Arrays.asList(
                new Genre(1L, "Homme"),
                new Genre(2L, "Femme")
        );
        when(genreRepository.findAll()).thenReturn(genres);

        List<Genre> result = service.getAllGenre();

        assertEquals(2, result.size());
        assertEquals("Femme", result.get(1).getLibelle());
    }

    @Test
    public void testGetAllGenresNotFound() {
        when(genreRepository.findAll()).thenReturn(Collections.emptyList());

        List<Genre> result = service.getAllGenre();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetGenreById() {
        Genre genre = new Genre(1L, "Homme");
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        Optional<Genre> result = service.getGenreById(1L);

        assertTrue(result.isPresent());
        assertEquals("Homme", result.get().getLibelle());
    }

    @Test
    public void testGetGenreByIdNotFound() {
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Genre> result = service.getGenreById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetGenreByLibelle() {
        Genre genre = new Genre(1L, "Homme");
        when(genreRepository.findByLibelle("Homme")).thenReturn(genre);

        Genre result = service.getGenreByLibelle("Homme");

        assertNotNull(result);
        assertEquals("Homme", result.getLibelle());
    }

    @Test
    public void testGetGenreByLibelleNotFound() {
        when(genreRepository.findByLibelle("Homme")).thenReturn(null);

        Genre result = service.getGenreByLibelle("Homme");

        assertNull(result);
    }

    @Test
    public void testCreateGenre() {
        Genre genre = new Genre(null, "NonBinaire");
        Genre saved = new Genre(3L, "NonBinaire");
        when(genreRepository.save(genre)).thenReturn(saved);

        Genre result = service.createGenre(genre);

        assertNotNull(result.getId());
        assertEquals("NonBinaire", result.getLibelle());
    }

    @Test
    public void testUpdateGenre() {
        Genre existing = new Genre(1L, "Homme");
        Genre updated= new Genre(null, "NonBinaire");

        when(genreRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(genreRepository.save(existing)).thenReturn(existing);

        Optional<Genre> result = service.updateGenre(1L, updated);

        assertTrue(result.isPresent());
        assertEquals("NonBinaire", result.get().getLibelle());
    }

    @Test
    public void testUpdateGenreNotFound() {
        Genre updated= new Genre(null, "NonBinaire");

        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Genre> result = service.updateGenre(1L, updated);

        assertFalse(result.isPresent());
    }

    @Test
    public void tesqtDeleteGenre() {
        when(genreRepository.existsById(3L)).thenReturn(true);
        doNothing().when(genreRepository).deleteById(3L);

        boolean result = service.deleteGenre(3L);

        assertTrue(result);
        verify(genreRepository, times(1)).deleteById(3L);
    }

    @Test
    public void tesqtDeleteGenreNotFound() {
        when(genreRepository.existsById(3L)).thenReturn(false);

        boolean result = service.deleteGenre(3L);

        assertFalse(result);
        verify(genreRepository, never()).deleteById(anyLong());
    }
}
