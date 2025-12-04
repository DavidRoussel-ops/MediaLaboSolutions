import { Component, OnInit, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../../services/patient';
import { PatientForm } from '../patient-form/patient-form';

@Component({
  selector: 'app-patient-detail',
  standalone : true,
  imports: [PatientForm],
  templateUrl: './patient-detail.html',
  styleUrl: './patient-detail.scss',
})
export class PatientDetail implements OnInit {

  patient = signal<any | null>(null);
  loaded = signal(false);
  showForm = false;

  constructor(
    private route : ActivatedRoute,
    private patientService : PatientService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.patientService.getPatientById(id).subscribe(data => {
      console.log("Patient reçu : ", data);
      this.patient.set(data);
      this.loaded.set(true);
    });
  }

  updatePatient(patient : any) {
    const id = this.route.snapshot.params['id'];
    console.log("Formulaire reçu MAJ : ", patient);
    this.patientService.updatePatient(id, patient).subscribe(() => {
      console.log("Données du patient bien mise à jour.");
      this.patientService.getPatientById(id).subscribe(data => {
        this.patient.set(data);
      });
      this.showForm = false;
    });
  }

  toogleForm() {
    this.showForm = !this.showForm;
  }
}
