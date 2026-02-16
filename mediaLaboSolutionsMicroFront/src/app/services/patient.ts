import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_URL } fron '../api.config';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private baseUrl = `${API_URL}/api/patients`;
  private headers = new HttpHeaders({
    "Authorization" : "Basic " + btoa("admin:admin123")
  });

  constructor(private http : HttpClient) {}

  getAllPatients() : Observable<any> {
    return this.http.get(this.baseUrl, { headers : this.headers });
  }

  getPatientById(id : number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, { headers : this.headers });
  }

  addPatient(patient : any) : Observable<any> {
    return this.http.post<any>(this.baseUrl, patient, { headers : this.headers });
  }

  updatePatient(id : number, patient : any) : Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, patient, { headers : this.headers });
  }
}
