import { Routes } from '@angular/router';
import { PatientList } from './pages/patient-list/patient-list';
import { PatientDetail } from './pages/patient-detail/patient-detail';

///Définition des routes principales
export const AppRoutes: Routes = [

  //Page d'accueil
  { path: '', component: PatientList },

  // Page de détail d'un patient
  { path: 'patients/:id', component: PatientDetail }
];
