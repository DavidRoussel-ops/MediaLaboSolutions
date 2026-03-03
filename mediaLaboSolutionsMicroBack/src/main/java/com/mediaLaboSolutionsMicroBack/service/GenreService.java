package com.mediaLaboSolutionsMicroBack.service;

import com.mediaLaboSolutionsMicroBack.entity.Genre;
import com.mediaLaboSolutionsMicroBack.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant la logique métier des genres
 */
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    /**
     * Injection du repository Genre
     * @param genreRepository
     */
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Récupère la list complète des genres
     * @return List<Genre>
     */
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    /**
     * Récupère un genre par son id
     * @param id
     * @return Optional<Genre>
     */
    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    /**
     * Récupère un genre par son libelle
     * @param libelle
     * @return Genre
     */
    public Genre getGenreByLibelle(String libelle) {
        return genreRepository.findByLibelle(libelle);
    }

    /**
     * Crée un genre
     * @param genre
     * @return
     */
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    /**
     * Met à jour un genre
     * @param id
     * @param updated
     * @return Optional<Genre>
     */
    public Optional<Genre> updateGenre(Long id, Genre updated) {
        return  genreRepository.findById(id).map(genre -> {
            genre.setLibelle(updated.getLibelle());
            return genreRepository.save(genre);
        });
    }

    /**
     * Supprime un genre
     * @param id
     * @return boolean
     */
    public boolean deleteGenre(Long id) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
