import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_URL } from '../api.config';

// Représente le résultat de l'évaluation du risque
export interface Assessment {
  patientId : number;
  patientName : string;
  age : number;
  gender : string;
  triggerCount : number;
  riskLevel : string;
  message : string;
}

// Service responsable des appels HTTP vers le microservice Risque.
@Injectable({
  providedIn : 'root'
})
export class AssessmentService {

  // URL de base pour l'évaluation du risque
  private apiUrl = `${API_URL}/assess`;

  constructor(private http : HttpClient) {}

  // Récupère l'évaluation du risque d'un patient
  getAssessment(patientId : number) : Observable<Assessment> {
    const headers = new HttpHeaders({
      Authorization : 'Basic ' + btoa("admin:admin123")
    });
    return this.http.get<Assessment>(`${this.apiUrl}/${patientId}`, { headers });
  }
}
