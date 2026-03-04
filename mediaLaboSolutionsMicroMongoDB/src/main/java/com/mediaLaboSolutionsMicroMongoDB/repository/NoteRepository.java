package com.mediaLaboSolutionsMicroMongoDB.repository;

import com.mediaLaboSolutionsMicroMongoDB.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository MongoDB pour l'entité Note
 */
public interface NoteRepository extends MongoRepository<Note, String> {
    /**
     * Recherche la liste de notes liées à un patient
     * @param patId
     * @return List<Note>
     */
    List<Note> findByPatId(Long patId);
}
