import { Component, OnInit, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../../services/patient';

@Component({
  selector: 'app-patient-detail',
  standalone : true,
  imports: [],
  templateUrl: './patient-detail.html',
  styleUrl: './patient-detail.scss',
})
export class PatientDetail implements OnInit {

  patient = signal<any | null>(null);
  loaded = signal(false);

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

}
