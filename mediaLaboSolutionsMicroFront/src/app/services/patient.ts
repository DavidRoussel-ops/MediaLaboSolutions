import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_URL } from '../api.config';

// Service responsable des appels HTTP vers le microservice Patient.
@Injectable({
  providedIn: 'root',
})
export class PatientService {
  // URL de base des patients
  private baseUrl = `${API_URL}/patients`;
  // En-têtes HTTP contenant l'authentification Basic
  private headers = new HttpHeaders({
    "Authorization" : "Basic " + btoa("admin:admin123")
  });

  constructor(private http : HttpClient) {}

  // Récupère la liste des patients
  getAllPatients() : Observable<any> {
    return this.http.get(this.baseUrl, { headers : this.headers });
  }

  // Récupère un patient par son id
  getPatientById(id : number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, { headers : this.headers });
  }

  // Ajoute un patient
  addPatient(patient : any) : Observable<any> {
    return this.http.post<any>(this.baseUrl, patient, { headers : this.headers });
  }

  // Met à jour un patient
  updatePatient(id : number, patient : any) : Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, patient, { headers : this.headers });
  }
}
