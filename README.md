# MediaLaboSolutions

MediaLaboSolutions est une application complète permettant la gestion de patients, de leurs notes médicales et de l’évaluation automatique de leur niveau de risque.  
Le projet repose sur une architecture **microservices**, un **API Gateway**, un **front-end Angular**, le tout dans un container **Docker** permettant un déploiement simple.

---

## 1. Objectifs du projet

- Concevoir une architecture microservices en Java Spring Boot
- Implémenter un front-end moderne en Angular
- Mettre en place un API Gateway (Spring Cloud Gateway)
- Conteneuriser l’ensemble avec Docker & Docker Compose

---

## 2. Architecture globale

L’application est composée de 5 services :

- **patient-service** : gestion des patients
- **notes-service** : gestion des notes médicales
- **risk-service** : calcul du niveau de risque
- **gateway-service** : point d’entrée unique sécurisé
- **front-end Angular** : interface utilisateur

Un schéma UML d’architecture sera ajouté ici.

---

## 3. Technologies utilisées

- **Java 17 / Spring Boot 3**
- **Spring Cloud Gateway**
- **Angular 17**
- **Docker & Docker Compose**
- **MySQL**
- **MongoDB**

---

## 4. Prérequis

- Docker
- Docker Compose
- 4 Go de RAM minimum recommandés

---

## 5. Installation & lancement

Cloner le projet :

```bash
git clone https://github.com/DavidRoussel-ops/MediaLaboSolutions.git
cd MediaLaboSolutions
```

## Lancer l'application

```bash
docker compose up --build
```

## Accéder à l'interface

- **Front-end** : http://localhost:4200
- **API Gateway** : http://localhost:8080

---

## 6. Détails des microservices

### patient-service
- CRUD complet des patients
- Stockage dans une base **MySQL**
- Endpoints principaux :
    - `GET /patients`
    - `GET /patients/{id}`
    - `POST /patients`
    - `PUT /patients/{id}`
    - `DELETE /patients/{id}`

---

### notes-service
- Gestion des notes médicales associées aux patients
- Stockage dans **MongoDB**
- Endpoints principaux :
    - `GET /notes/{patientId}`
    - `POST /notes/{patientId}`
    - `PUT /notes/{noteId}`
    - `DELETE /notes/{noteId}`

---

### risk-service
- Analyse du risque d’un patient selon ses notes
- Retourne un niveau parmi :
    - `NONE`
    - `BORDERLINE`
    - `IN_DANGER`
    - `EARLY_ONSET`
- Endpoint principal :
    - `GET /assess/{patientId}`

---

### gateway-service
- Point d’entrée unique de l’application
- Routage vers les microservices
- Sécurisé via **HTTP Basic Auth**
- Simplifie les appels du front-end
- Exemple de routes :
    - `/patients/**` → patient-service
    - `/notes/**` → notes-service
    - `/risk/**` → risk-service

---

### front-end Angular
- Interface utilisateur complète
- Pages :
    - Liste des patients
    - Détail d’un patient
    - Ajout / modification d’un patient
    - Notes du patient
- Intégration du **code couleur du risque** :
    - NONE → bleu
    - BORDERLINE → jaune
    - IN_DANGER → orange
    - EARLY_ONSET → rouge
- Toutes les requêtes passent par le **Gateway**

---

## 7. Sécurité

- Authentification **HTTP Basic**
- Le Gateway filtre et redirige les requêtes
- Aucun microservice n’est exposé directement
- Architecture sécurisée et cloisonnée

---

## 8. Diagrammes UML

Les diagrammes suivants seront ajoutés :

- **Diagramme d’architecture**
  ![Diagramme Architecture.drawio.png](/diagrammes/Diagramme_ArchitectureV1.2.drawio.png)
- **Diagramme de classes**
  ![Diagramme Architecture.drawio.png](/diagrammes/UML_Class.drawio.png)
- **Diagramme de séquence**

---

## 9. Analyse Green Code

### Enjeux du Green Code
Le Green Code vise à réduire l’impact environnemental du logiciel en optimisant la consommation de ressources (CPU, mémoire, réseau, stockage).

### Analyse du projet
- Architecture microservices → claire mais consomme plus de ressources
- Images Docker Java relativement lourdes
- Logs Spring verbeux par défaut
- Bundle Angular optimisable
- Pas de compression GZIP au niveau du Gateway

### Recommandations
- Utiliser des images Docker plus légères (Temurin Alpine)
- Activer GZIP dans le Gateway
- Compresser les images du front (WebP)
- Réduire les logs en production
- Mettre en place un cache côté backend

---

## 10. Tests

- Tests unitaires backend (si présents)
- Tests manuels via Postman
- Vérification des routes via Gateway

---

## 11. Améliorations futures

- Authentification avancée (JWT)
- Optimisations Green Code plus poussées
- Mise en place d’un pipeline CI/CD (GitHub Actions)

---

## 12. Auteur

Projet réalisé par **David Roussel**.

