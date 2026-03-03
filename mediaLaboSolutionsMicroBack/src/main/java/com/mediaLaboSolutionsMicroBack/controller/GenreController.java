package com.mediaLaboSolutionsMicroBack.controller;

import com.mediaLaboSolutionsMicroBack.entity.Genre;
import com.mediaLaboSolutionsMicroBack.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour le CRUD sur les genres.
 */
@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    /**
     * Constructeur genreService
     * @param genreService
     */
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Récupère la liste des genres
     * @return List<Genre>
     */
    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenre();
    }

    /**
     * Récupère un genre par son id
     * @param id
     * @return ResponseEntity<Genre>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        return genreService.getGenreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouveau genre
     * @param genre
     * @return ResponseEntity<Genre>
     */
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre saved = genreService.createGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Met à jour un genre
     * @param id
     * @param updated
     * @return ResponseEntity<Genre>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre updated) {
        return genreService.updateGenre(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime un genre
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        return genreService.deleteGenre(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
