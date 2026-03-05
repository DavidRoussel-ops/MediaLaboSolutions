import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

// Composant du formulaire de création ou modification d'un patient
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

  genres = [
    { id : 1, libelle : 'F'},
    { id : 2, libelle : 'M'}
  ];

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

  // Pré-rempli le formulaire quand le données patient sont présente
  ngOnInit() : void {
    if (this.patientData) {
      this.patientForm.patchValue(this.patientData);
    }
  }

  // Soumet le formulaire quand il est valide
  onSubmit() : void {
    if (this.patientForm.valid) {
      this.formSubmit.emit(this.patientForm.value);
    }
  }

  // Met à jour le champ genre
  onGenreChange(event : any) {
    const selectedId = +event.target.value;
    const selectedGenre = this.genres.find(genre => genre.id === selectedId);
    if (selectedGenre) {
      this.patientForm.get('genre')?.setValue({
        id : selectedGenre.id,
        libelle : selectedGenre.libelle
      });
    }
  }

}
