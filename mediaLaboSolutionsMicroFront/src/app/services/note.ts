import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { API_URL } from '../api.config';

// Représente une note médicale d'un patient
export interface Note {
    id? : string;
    patId : number;
    patient : string;
    note : string;
}
// Service responsable des appels HTTP vers le microservice Note.
@Injectable({
    providedIn : 'root'
})
export class NoteService {
    // URL de base des notes
    private baseUrl = `${API_URL}/notes`;
    // En-têtes HTTP contenant l'authentification Basic
    private headers = new HttpHeaders({
        "Authorization" : "Basic " + btoa("admin:admin123")
    });

    constructor(private http : HttpClient) {}

    // Récupère toutes les notes d'un patient
    getNotesByPatient(patId : number) : Observable<Note[]> {
        return this.http.get<Note[]>(`${this.baseUrl}/patient/${patId}`, { headers : this.headers });
    }

    // Ajoute une note
    addNote(note : Note) : Observable<Note> {
        return this.http.post<Note>(this.baseUrl, note, { headers : this.headers });
    }

    // Met à jour une note
    updateNote(id : string, note : Note) : Observable<Note> {
        return this.http.put<Note>(`${this.baseUrl}/${id}`, note, { headers : this.headers });
    }

    //Supprime une note
    deleteNote(id : string) : Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${id}`, { headers : this.headers });
    }
}
