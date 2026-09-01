# 📚 Library API

[![CI](https://github.com/amelia-decobert/library-api/actions/workflows/ci.yml/badge.svg)](https://github.com/amelia-decobert/library-api/actions/workflows/ci.yml)

API REST et application full-stack développées avec **Java 21** et **Spring Boot**, permettant de gérer une bibliothèque et ses emprunts.

Ce projet, réalisé en autonomie dans le cadre de ma spécialisation en développement **Backend Java**, est avant tout un projet d'apprentissage.

L'objectif est de construire progressivement une application proche d'un projet professionnel en intégrant des technologies et bonnes pratiques couramment utilisées dans l'écosystème Java/Spring :

**Java · Spring Boot · PostgreSQL · JPA/Hibernate · Spring Security · JWT · Flyway · Swagger/OpenAPI · JUnit · Mockito · Testcontainers · Docker · Angular · GitHub Actions**

> **Focus principal : Backend Java**
>
> Le frontend Angular reste volontairement minimaliste. Il est utilisé comme client de démonstration afin de consommer l'API REST et de mettre en pratique l'authentification côté frontend. Le développement frontend n'est pas l'objectif principal.

---

## 🎯 Objectifs du projet

Ce projet me permet de mettre en pratique :

* la conception d'une API REST
* l'architecture en couches avec Spring Boot
* la persistance avec JPA/Hibernate et PostgreSQL
* la validation des données
* la gestion centralisée des erreurs
* la pagination, le tri et la recherche
* la conception de logique métier
* la sécurisation d'une API avec Spring Security et JWT
* la gestion des utilisateurs et des rôles
* l'écriture de premiers tests unitaires et d'intégration
* l'utilisation de Testcontainers
* la gestion des migrations de base de données avec Flyway
* la documentation d'une API avec OpenAPI/Swagger
* la conteneurisation avec Docker et Docker Compose
* l'intégration d'un frontend Angular permettant de consommer l'API
* la mise en place d'une CI avec GitHub Actions

---

## 🚧 État du projet

| Fonctionnalité                            | Statut |
|-------------------------------------------|:------:|
| Architecture Controller / Service / Repository |   ✅   |
| API REST                                  |   ✅   |
| CRUD                                      |   ✅   |
| DTO                                       |   ✅   |
| Validation                                |   ✅   |
| Gestion centralisée des erreurs           |   ✅   |
| PostgreSQL                                |   ✅   |
| Spring Data JPA / Hibernate               |   ✅   |
| Spring Security                           |   ✅   |
| JWT                                       |   ✅   |
| Flyway                                    |   ✅   |
| Swagger / OpenAPI                         |   ✅   |
| Docker / Docker Compose                   |   ✅   |
| Tests unitaires avec JUnit 5              |   🟡   |
| Tests avec Mockito                        |   🟡   |
| Tests d'intégration                       |   🟡   |
| Testcontainers                            |   🟡   |
| Frontend Angular                          |   ✅   |
| GitHub Actions / CI                       |   ✅   |

> 🟡 **En cours** — fonctionnalité commencée mais pas encore finalisée

---

## 🛠️ Technologies utilisées

| Technologie             | Usage                                             |
|-------------------------|---------------------------------------------------|
| **Java 21**             | Langage                                           |
| **Spring Boot 4.1.0**   | Framework principal                               |
| **Spring Web**          | Création de l'API REST                            |
| **Spring Validation**   | Validation des données entrantes                  |
| **Spring Security**     | Authentification et autorisation                  |
| **JWT**                 | Authentification stateless                        |
| **Spring Data JPA**     | Persistance des données                           |
| **Hibernate**           | ORM                                               |
| **PostgreSQL**          | Base de données relationnelle                     |
| **Flyway**              | Gestion des migrations de base de données         |
| **SpringDoc / OpenAPI** | Documentation de l'API                            |
| **JUnit 5**             | Tests unitaires et d'intégration                  |
| **Mockito**             | Mocking et isolation des dépendances              |
| **Testcontainers**      | Tests avec une véritable instance PostgreSQL      |
| **Docker**              | Conteneurisation de l'application                 |
| **Docker Compose**      | Orchestration des services en environnement local |
| **Angular**             | Client frontend minimaliste de démonstration      |
| **GitHub Actions**      | Intégration Continue                              |
| **Lombok**              | Réduction du code répétitif                       |
| **Maven**               | Gestion des dépendances et build                  |

---

## 📋 Prérequis

* IntelliJ IDEA (IDE recommandé)
* Java 21+
* Maven
* Docker / Docker Compose
* Postman (ou autre client HTTP)
* Node.js / npm (frontend)

---

## 🚀 Installation et lancement

### API

### 🐳 Option recommandée — Docker Compose

Cloner le repository :

```bash
git clone [URL REPO]
cd library-api
````

Configurer ensuite les paramètres de connexion dans la configuration locale.


Lancer l'ensemble des services :

```bash
docker compose up --build
```

L'application démarre avec :

* l'API Spring Boot
* PostgreSQL
* le réseau Docker nécessaire à la communication entre les services

Les migrations Flyway sont exécutées automatiquement au démarrage de l'application.

L'API est accessible à :

```text
http://localhost:8080
```

Pour arrêter les services :

```bash
docker compose down
```

Pour arrêter les services et supprimer également les volumes :

```bash
docker compose down -v
```

Vérifier que l'application fonctionne :

```bash
curl http://localhost:8080/health
```

Réponse attendue :

```json
{
  "status": "UP"
}
```

### Frontend

Depuis le dossier front (/library-frontend)

```bash
npm install
npm start
```

---

## 🏗️ Architecture

L'application suit actuellement une architecture en couches :

```text
Client
    ↓
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL / JPA
```

Les principales responsabilités sont séparées entre :

```text
config/         → configuration de l'application
controller/     → endpoints REST
dto/            → objets d'entrée/sortie de l'API
exception/      → gestion centralisée des erreurs
mapper/         → conversion Entity ↔ DTO
model/          → entités JPA
repository/     → accès aux données
security/       → authentification & JWT
service/        → logique métier
```

La sécurité repose sur **Spring Security** et **JWT**, avec une gestion des rôles
permettant notamment de distinguer les utilisateurs et les administrateurs.

---

## 📚 Fonctionnalités

### Livres

* Consultation des livres
* Pagination
* Tri
* Recherche par titre
* Recherche par auteur
* Filtrage par année
* Création d'un livre
* Modification d'un livre
* Suppression d'un livre

### Auteurs

* Consultation des auteurs
* Consultation des livres d'un auteur
* Création d'un auteur

### Catégories

* Consultation des catégories
* Création d'une catégorie

### Statistiques

* Nombre total de livres
* Nombre total d'auteurs
* Nombre total de catégories

### Emprunts

* Emprunt d'un livre
* Consultation de ses propres emprunts
* retour d'un livre
* Consultation de l'ensemble des emprunts en tant qu'administrateur
* Consultation des retards de retour
* Limite du nombre d'emprunts

### Utilisateurs

* Inscription
* Connexion / Déconnexion
* Authentification JWT
* Gestion des rôles USER / ADMIN
* Protection des ressources selon le rôle

### Sécurité

* Spring Security
* Authentification JWT
* Protection des endpoints
* Authentification stateless
* Gestion des rôles & autorisations

---

## 🔐 Authentification

L'API utilise **Spring Security** et **JWT** pour sécuriser les endpoints.

Le principe est le suivant :

```text
Client
  │
  │ POST /auth/login
  ▼
Spring Security
  │
  │ JWT
  ▼
Client
  │
  │ Authorization: Bearer <token>
  ▼
JWT Filter
  │
  ▼
Security Context
  │
  ▼
Controller
```

Les endpoints nécessitant une authentification doivent recevoir un token JWT valide.

---

## 🗄️ Gestion de la base de données

La persistance repose sur :

* PostgreSQL
* Spring Data JPA
* Hibernate

Les évolutions du schéma de base de données sont gérées avec **Flyway**.

Les migrations sont versionnées afin de permettre de reproduire l'évolution du schéma de manière fiable.

```text
V1
 ↓
V2
 ↓
V3
 ↓
...
```

---

## 📖 Documentation de l'API

L'API est documentée avec **OpenAPI / Swagger**.

SwaggerUI permet notamment de :

* consulter les endpoints disponibles
* visualiser les paramètres et réponses
* consulter les schémas de requêtes et réponses
* utiliser l'authentification Bearer JWT

Une fois l'application démarrée, SwaggerUI est accessible à :

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 🔌 API

Les principaux endpoints disponibles sont présentés ci-dessous.

Pour consulter la documentation complète et tester l'API, utiliser **SwaggerUI**.

### Authentification

| Méthode | Endpoint         | Description                           |
| ------- | ---------------- | ------------------------------------- |
| POST    | `/auth/register` | Création d'un utilisateur             |
| POST    | `/auth/login`    | Authentification et génération du JWT |

### Livres

| Méthode | Endpoint                 | Description                           |
| ------- | ------------------------ | ------------------------------------- |
| GET     | `/books`                 | Liste paginée des livres              |
| GET     | `/books/{id}`            | Détails d'un livre                    |
| GET     | `/books/author/{author}` | Livres d'un auteur                    |
| GET     | `/books/recent`          | Livres publiés après une année donnée |
| GET     | `/books/search`          | Recherche combinée                    |
| POST    | `/books`                 | Création d'un livre                   |
| PUT     | `/books/{id}`            | Modification d'un livre               |
| DELETE  | `/books/{id}`            | Suppression d'un livre                |

### Auteurs

| Méthode | Endpoint              | Description          |
| ------- | --------------------- | -------------------- |
| GET     | `/authors`            | Liste des auteurs    |
| GET     | `/authors/{id}`       | Détails d'un auteur  |
| GET     | `/authors/{id}/books` | Livres d'un auteur   |
| POST    | `/authors`            | Création d'un auteur |

### Catégories

| Méthode | Endpoint      | Description              |
| ------- | ------------- | ------------------------ |
| GET     | `/categories` | Liste des catégories     |
| POST    | `/categories` | Création d'une catégorie |

### Statistiques

| Méthode | Endpoint      | Description                               |
| ------- | ------------- | ----------------------------------------- |
| GET     | `/statistics` | Statistiques générales de la bibliothèque |

### Emprunts

| Méthode | Endpoint             | Description                         |
|---------|----------------------|-------------------------------------|
| GET     | `/loans/me`          | Emprunts de l'utilisateur connecté  |
| GET     | `/loans`             | List de tous les emprunts - ADMIN   |
| GET     | `/loans/overdue`     | Liste des retours en retard - ADMIN |
| POST    | `/loans`             | Création d'un emprunt               |
| POST    | `/books/{id}/borrow` | Emprunter un livre                  |
| PUT     | `/loans/{id}/return` | Retourner un emprunt                |


---

## 🧪 Tests

Le projet intègre progressivement une stratégie de tests comprenant :

* **JUnit 5**
* **Mockito**
* **Spring Boot Test**
* **Testcontainers** (pour tester la persistance avec une véritable instance PostgreSQL)

Des premiers tests unitaires et d'intégration ont été mis en place
afin de pratiquer les différentes approches.

La stratégie de tests et la couverture du projet restent toutefois à approfondir.

---

## 🐳 Conteneurisation

L'application et sa base de données peuvent être exécutées avec **Docker / Docker Compose**.

L'objectif est de fournir un environnement reproductible permettant de lancer les différents services du projet sans nécessiter une installation locale complète de PostgreSQL.

```text
              Docker Compose
                    │
          ┌─────────┴─────────┐
          ▼                   ▼
     Spring Boot          PostgreSQL
       Container           Container
          │                   │
          └─────────┬─────────┘
                    │
                 Network
```

---

## 🔄 Intégration continue

Le projet utilise **GitHub Actions** afin d'automatiser certaines vérifications à chaque modification du code.

Le pipeline CI actuel comprend notamment :

```text
Push / Pull Request
        │
        ▼
   ┌─────────┐
   │  Tests  │
   └────┬────┘
        │
        ▼
   ┌─────────┐
   │  Build  │
   └────┬────┘
        │
        ▼
      ✅ CI
```

L'objectif est de détecter automatiquement les régressions et de vérifier que le projet peut être construit correctement.


> Le **CD (Continuous Delivery / Deployment)** n'est volontairement pas implémenté sur ce projet d'apprentissage. Le déploiement automatisé sera expérimenté sur un futur projet destiné à être réellement mis en production.

---

## 🖥️ Frontend

Un frontend **Angular minimaliste** accompagne le projet.

Il a pour objectif principal de consommer l'API REST et de fournir une interface permettant de démontrer certaines fonctionnalités :

* connexion utilisateur
* authentification JWT
* consultation des livres
* consultation du détail d'un livre
* emprunt d'un livre
* consultation de ses emprunts
* consultation de tous les emprunts pour un administrateur
* gestion basique des livres pour un administrateur

Le frontend reste volontairement limité afin de conserver le **backend Java/Spring Boot comme cœur du projet**.

---

## 📌 Évolutions possibles

Le projet pourra continuer à évoluer avec notamment :

* approfondissement de la couverture de tests
* amélioration de l'interface Angular
* ajout de fonctionnalités métier
