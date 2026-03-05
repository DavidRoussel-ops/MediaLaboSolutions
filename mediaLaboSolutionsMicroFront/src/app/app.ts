import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { PatientList } from './pages/patient-list/patient-list';
import { PatientDetail } from './pages/patient-detail/patient-detail';

// Composant racine de l'application
@Component({
  selector: 'app-root',
  standalone : true,
  imports: [RouterOutlet, HttpClientModule, PatientList, PatientDetail],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  // Titre de l'application
  protected readonly title = signal('mediaLaboSolutionsMicroFront');
}
