import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { AppRoutes } from './app.routes';

//Configuration principale de l'application
export const appConfig: ApplicationConfig = {
  providers: [
    //Active les gestionnaires d'erreurs du navigateur
    provideBrowserGlobalErrorListeners(),

    //Fournit la configuration du router
    provideRouter(AppRoutes)
  ]
};
