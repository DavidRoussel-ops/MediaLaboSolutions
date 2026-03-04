package com.mediaLaboSolutionsMicroRisque.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Gestion global des exceptions
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère les RuntimeException
     * @param ex
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        // ressource non trouvée
        if (ex.getMessage() != null && ex.getMessage().contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        // erreur interne
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body("Erreur interne");
    }
}
