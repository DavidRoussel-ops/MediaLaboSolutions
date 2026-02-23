import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PatientService } from '../../services/patient';
import { HttpClientModule } from '@angular/common/http';
import { PatientForm } from '../patient-form/patient-form';
import { Header } from '../../header/header';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule, PatientForm, Header],
  templateUrl: './patient-list.html',
  styleUrls: ['./patient-list.scss']
})
export class PatientList implements OnInit {
  patients = signal<any[]>([]);
  loaded = signal(false);
  showForm = false;

  constructor(private patientService: PatientService) {}

  ngOnInit(): void {
    this.patientService.getAllPatients().subscribe(data => {
      console.log("Patients reçu du back :", data);
      this.patients.set(data);
      this.loaded.set(true);
    });
  }

  /*addPatient(patient : any) {
    console.log("Formulaire reçu ajout : ", patient);
    this.patientService.addPatient(patient).subscribe(() => {
      console.log("Patient bien enregistrer.");
      this.loadPatients();
      this.showForm = false;
    })
  }*/

  addPatient(patient: any): void {
  console.log("Formulaire reçu (ajout) :", patient);
  this.patientService.addPatient(patient).subscribe(() => {
    console.log("Patient ajouté !");
    this.loadPatients();
  });
}

  loadPatients() {
    this.patientService.getAllPatients().subscribe(data => {
      this.patients.set(data);
    })
  }

  toogleForm() {
    this.showForm = !this.showForm;
  }
}
