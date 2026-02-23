import { Component, OnInit, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../../services/patient';
import { PatientForm } from '../patient-form/patient-form';
import { Note, NoteService } from '../../services/note';
import { NoteForm } from '../note-form/note-form';
import {Assessment, AssessmentService } from '../../services/assessment';
import { Header } from '../../header/header';

@Component({
  selector: 'app-patient-detail',
  standalone : true,
  imports: [PatientForm, NoteForm, Header],
  templateUrl: './patient-detail.html',
  styleUrl: './patient-detail.scss',
})
export class PatientDetail implements OnInit {

  patient = signal<any | null>(null);
  loaded = signal(false);
  showForm = false;
  patientId! : number;
  patientName : string = '';
  notes = signal<Note[]>([]);
  showFormNote = false;

  assessment = signal<Assessment | null>(null);

  constructor(
    private route : ActivatedRoute,
    private patientService : PatientService,
    private noteService : NoteService,
    private assessmentService : AssessmentService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.patientService.getPatientById(id).subscribe(data => {
      console.log("Patient reçu : ", data);
      this.patient.set(data);
      this.loaded.set(true);
    });
    this.patientId = Number(this.route.snapshot.paramMap.get('id'));
    this.patientService.getPatientById(this.patientId).subscribe(p => this.patientName = p.nom);
    this.loadNotes();
    this.loadAssessment();
  }

  loadAssessment() : void {
    this.assessmentService.getAssessment(this.patientId).subscribe(a => {
      console.log("Assessment reçu : ", a);
      this.assessment.set(a);
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

  toogleFormNote() {
    this.showFormNote = !this.showFormNote;
  }

  loadNotes() : void {
    this.noteService.getNotesByPatient(this.patientId).subscribe(data => {
      this.notes.set(data);
    });
  }

  handleNoteAdded(noteText : string) : void {
    const note : Note = {
      patId : this.patientId,
      patient : this.patientName,
      note : noteText
    };

    this.noteService.addNote(note).subscribe(saved => {
      this.notes.update(current => [...current, saved]);
      this.loadAssessment();
    });
  }
}
