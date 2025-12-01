import { Routes } from '@angular/router';
import { PatientList } from './pages/patient-list/patient-list';
import { PatientDetail } from './pages/patient-detail/patient-detail';

export const AppRoutes: Routes = [
  { path: '', component: PatientList },
  { path: 'patients/:id', component: PatientDetail }
];
