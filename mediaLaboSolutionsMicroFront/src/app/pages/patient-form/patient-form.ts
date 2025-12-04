import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-patient-form',
  standalone : true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './patient-form.html',
  styleUrl: './patient-form.scss',
})
export class PatientForm {

  @Input() patientData : any | null = null;
  @Input() mode : 'create' | 'edit' = 'create';
  @Output() formSubmit = new EventEmitter<any>();

  patientForm : FormGroup;

  constructor(private fb : FormBuilder) {
    this.patientForm = this.fb.group({
      nom : ['', Validators.required],
      prenom : ['', Validators.required],
      dateNaissance : ['', Validators.required],
      genre : this.fb.group({
        id : [null, Validators.required],
        libelle : ['', Validators.required]
      }),
      adresse : this.fb.group({
        id : [null],
        libelle : [''] 
      }),
      telephone : this.fb.group({
        id : [null],
        numero : ['']
      }),
    });
  }

  ngOnInit() : void {
    if (this.patientData) {
      this.patientForm.patchValue(this.patientData);
    }
  }

  onSubmit() : void {
    if (this.patientForm.valid) {
      this.formSubmit.emit(this.patientForm.value);
    }
  }

}
