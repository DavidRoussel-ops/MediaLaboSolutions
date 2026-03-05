import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PatientService } from '../../services/patient';
import { HttpClientModule } from '@angular/common/http';
import { PatientForm } from '../patient-form/patient-form';
import { Header } from '../../header/header';

// Composant affichant la liste des patients
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

  // Charge la liste des patients au démarrage du composant
  ngOnInit(): void {
    this.patientService.getAllPatients().subscribe(data => {
      console.log("Patients reçu du back :", data);
      this.patients.set(data);
      this.loaded.set(true);
    });
  }

  // Ajoute un patient et recharge la liste
  addPatient(patient: any): void {
  console.log("Formulaire reçu (ajout) :", patient);
  this.patientService.addPatient(patient).subscribe(() => {
    console.log("Patient ajouté !");
    this.loadPatients();
  });
}

  // Recharge la liste des patients 
  loadPatients() {
    this.patientService.getAllPatients().subscribe(data => {
      this.patients.set(data);
    })
  }

  // Affiche ou masque le formulaire
  toogleForm() {
    this.showForm = !this.showForm;
  }
}
