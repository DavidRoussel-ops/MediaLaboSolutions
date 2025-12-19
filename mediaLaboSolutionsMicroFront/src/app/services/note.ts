import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

export interface Note {
    id? : string;
    patId : number;
    patient : string;
    note : string;
}

@Injectable({
    providedIn : 'root'
})
export class NoteService {
    private baseUrl = "/api/notes";
    private headers = new HttpHeaders({
        "Authorization" : "Basic " + btoa("admin:admin1234!")
    });

    constructor(private http : HttpClient) {}

    getNotesByPatient(patId : number) : Observable<Note[]> {
        return this.http.get<Note[]>(`${this.baseUrl}/patient/${patId}`);
    }

    addNote(note : Note) : Observable<Note> {
        return this.http.post<Note>(this.baseUrl, note);
    }

    updateNote(id : string, note : Note) : Observable<Note> {
        return this.http.put<Note>(`${this.baseUrl}/${id}`, note);
    }

    deleteNote(id : string) : Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }
}