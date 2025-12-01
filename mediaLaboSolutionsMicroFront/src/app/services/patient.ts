import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private baseUrl = "/api/patients";
  private headers = new HttpHeaders({
    "Authorization" : "Basic " + btoa("admin:admin1234!")
  });

  constructor(private http : HttpClient) {}

  getAllPatients() : Observable<any> {
    return this.http.get(this.baseUrl, { headers : this.headers });
  }

  getPatientById(id : number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, { headers : this.headers });
  }
}
