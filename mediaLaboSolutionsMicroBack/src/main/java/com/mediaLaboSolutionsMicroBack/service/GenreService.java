package com.mediaLaboSolutionsMicroBack.mediaLaboSolutionsMicroBack.service;

import com.mediaLaboSolutionsMicroBack.mediaLaboSolutionsMicroBack.entity.Genre;
import com.mediaLaboSolutionsMicroBack.mediaLaboSolutionsMicroBack.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    public Genre getGenreByLibelle(String libelle) {
        return genreRepository.findByLibelle(libelle);
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Optional<Genre> updateGenre(Long id, Genre updated) {
        return  genreRepository.findById(id).map(genre -> {
            genre.setLibelle(updated.getLibelle());
            return genreRepository.save(genre);
        });
    }

    public boolean deleteGenre(Long id) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
