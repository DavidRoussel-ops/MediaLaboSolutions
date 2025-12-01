import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PatientService } from '../../services/patient';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule],
  templateUrl: './patient-list.html',
  styleUrls: ['./patient-list.scss']
})
export class PatientList implements OnInit {
  patients = signal<any[]>([]);
  loaded = signal(false);

  constructor(private patientService: PatientService) {}

  ngOnInit(): void {
    this.patientService.getAllPatients().subscribe(data => {
      console.log("Patients reçu du back :", data);
      this.patients.set(data);
      this.loaded.set(true);
    });
  }
}