import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Assessment {
  patientId : number;
  patientName : string;
  age : number;
  gender : string;
  triggerCount : number;
  riskLevel : string;
  message : string;
}

@Injectable({
  providedIn : 'root'
})
export class AssessmentService {

  private apiUrl = '/api/assess';

  constructor(private http : HttpClient) {}

  getAssessment(patientId : number) : Observable<Assessment> {
    const headers = new HttpHeaders({
      Authorization : 'Basic ' + btoa("admin:admin123")
    });
    return this.http.get<Assessment>(`${this.apiUrl}/${patientId}`, { headers });
  }
}
