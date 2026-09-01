# 📚 Library API

> 🚧 **Projet en cours de développement**

API REST et application full-stack développées avec **Java 21** et **Spring Boot**, permettant de gérer une bibliothèque.

Ce projet, réalisé en autonomie dans le cadre de ma spécialisation en développement **Backend Java**, est avant tout un projet d'apprentissage.

L'objectif est de construire progressivement une application proche d'un projet professionnel en intégrant des technologies et bonnes pratiques couramment utilisées dans l'écosystème Java/Spring :

**Java · Spring Boot · PostgreSQL · JPA/Hibernate · Spring Security · JWT · Flyway · Swagger/OpenAPI · JUnit · Mockito · Testcontainers · Docker · Angular · CI/CD**

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
* les tests unitaires et d'intégration
* l'utilisation de Testcontainers
* la gestion des migrations de base de données avec Flyway
* la documentation d'une API avec OpenAPI/Swagger
* la conteneurisation avec Docker et Docker Compose
* l'intégration d'un frontend Angular avec une API REST
* l'automatisation avec CI/CD

---

## 🚧 État du projet

| Fonctionnalité                               | Statut |
|----------------------------------------------|:------:|
| Architecture Controller / Service / Repository |   ✅   |
| API REST                                     |   ✅   |
| CRUD                                         |   ✅   |
| DTO                                          |   ✅   |
| Validation                                   |   ✅   |
| Gestion centralisée des erreurs              |   ✅   |
| PostgreSQL                                   |   ✅   |
| Spring Data JPA / Hibernate                  |   ✅   |
| Spring Security                              |   ✅   |
| JWT                                          |   ✅   |
| Flyway                                       |   ✅   |
| Swagger / OpenAPI                            |   ✅   |
| Docker / Docker Compose                      |   ✅   |
| Tests unitaires avec JUnit 5                 |   🟡   |
| Tests avec Mockito                           |   🟡   |
| Tests d'intégration                          |   🟡   |
| Testcontainers                               |   🟡   |
| Frontend Angular                             |   🟡   |
| GitHub Actions / CI/CD                       |   ⏳   |

> 🟡 **En cours** — fonctionnalité commencée mais pas encore finalisée
>
> ⏳ **À venir** — fonctionnalité prévue dans la roadmap

---

## 🛠️ Technologies utilisées

| Technologie             | Usage                                              |
|-------------------------|----------------------------------------------------|
| **Java 21**             | Langage                                             |
| **Spring Boot 4.1.0**   | Framework principal                                 |
| **Spring Web**          | Création de l'API REST                              |
| **Spring Validation**   | Validation des données entrantes                    |
| **Spring Security**     | Authentification et autorisation                    |
| **JWT**                 | Authentification stateless                          |
| **Spring Data JPA**     | Persistance des données                             |
| **Hibernate**           | ORM                                                  |
| **PostgreSQL**          | Base de données relationnelle                       |
| **Flyway**              | Gestion des migrations de base de données           |
| **SpringDoc / OpenAPI** | Documentation de l'API                              |
| **JUnit 5**             | Tests unitaires et d'intégration                    |
| **Mockito**             | Mocking et isolation des dépendances                |
| **Testcontainers**      | Tests avec une véritable instance PostgreSQL        |
| **Docker**              | Conteneurisation de l'application                   |
| **Docker Compose**      | Orchestration des services en environnement local   |
| **Angular**             | Client frontend minimaliste de démonstration        |
| **TypeScript**          | Développement du client frontend                    |
| **Lombok**              | Réduction du code répétitif                          |
| **Maven**               | Gestion des dépendances et build                    |

---

## 📋 Prérequis

### Pour le lancement avec Docker

* Docker
* Docker Compose

### Pour le développement local

* Java 21+
* Maven
* PostgreSQL
* IntelliJ IDEA (IDE recommandé)

### Pour tester l'API

* Postman (ou autre client HTTP)
* SwaggerUI

---

## 🚀 Installation et lancement

### 🐳 Option recommandée — Docker Compose

Cloner le repository :

```bash
git clone https://github.com/<votre-nom>/library-api.git
cd library-api
````

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

---

### 💻 Option alternative — Lancement local

Il est également possible de lancer l'application directement depuis IntelliJ IDEA.

Créer une base de données PostgreSQL :

```sql
CREATE USER <username> WITH PASSWORD '<password>';
CREATE DATABASE library OWNER <username>;
```

Configurer ensuite les paramètres de connexion dans la configuration locale.

Lancer l'application :

```bash
./run-dev.sh
```

L'API démarre par défaut sur :

```text
http://localhost:8080
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

---

## 🏗️ Architecture

L'application suit actuellement une architecture en couches :

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
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
security/       → configuration et composants de sécurité
service/        → logique métier
```

La sécurité est organisée autour de **Spring Security** et **JWT**.

---

## 📚 Fonctionnalités

### 📖 Livres

* Consultation des livres
* Pagination
* Tri
* Recherche par titre
* Recherche par auteur
* Filtrage par année
* Création d'un livre
* Modification d'un livre
* Suppression d'un livre

### 👤 Auteurs

* Consultation des auteurs
* Consultation des livres d'un auteur
* Création d'un auteur

### 🏷️ Catégories

* Consultation des catégories
* Création d'une catégorie

### 📊 Statistiques

* Nombre total de livres
* Nombre total d'auteurs
* Nombre total de catégories

### 🔐 Sécurité

* Spring Security
* Authentification JWT
* Protection des endpoints
* Authentification stateless
* Gestion des utilisateurs et des rôles

### 🗄️ Base de données

* PostgreSQL
* JPA / Hibernate
* Migrations versionnées avec Flyway

### 📖 Documentation

* Documentation OpenAPI
* Interface SwaggerUI

### 🐳 Conteneurisation

* Docker
* Docker Compose
* Conteneurisation de l'application Spring Boot
* PostgreSQL conteneurisé
* Configuration des services
* Persistance des données avec volumes

### 🖥️ Frontend

Un frontend Angular minimaliste est développé afin de :

* consommer l'API REST
* tester le parcours d'authentification
* envoyer le JWT aux requêtes protégées
* démontrer l'intégration frontend ↔ backend

> Le frontend est volontairement limité afin de conserver le focus principal du projet sur le développement Backend Java.

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

### Authentification avec Angular

Le frontend suit progressivement le flux suivant :

```text
Login Component
      │
      │ POST /auth/login
      ▼
Spring Boot
      │
      │ JWT
      ▼
Auth Service
      │
      ▼
HTTP Interceptor
      │
      │ Authorization: Bearer <JWT>
      ▼
Spring Security
      │
      ▼
Protected API
```

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

Pour consulter la documentation complète, les paramètres, les schémas de requêtes/réponses et tester l'API, utiliser **SwaggerUI**.

### 📖 Livres

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

### 👤 Auteurs

| Méthode | Endpoint              | Description          |
| ------- | --------------------- | -------------------- |
| GET     | `/authors`            | Liste des auteurs    |
| GET     | `/authors/{id}`       | Détails d'un auteur  |
| GET     | `/authors/{id}/books` | Livres d'un auteur   |
| POST    | `/authors`            | Création d'un auteur |

### 🏷️ Catégories

| Méthode | Endpoint      | Description              |
| ------- | ------------- | ------------------------ |
| GET     | `/categories` | Liste des catégories     |
| POST    | `/categories` | Création d'une catégorie |

### 📊 Statistiques

| Méthode | Endpoint      | Description                               |
| ------- | ------------- | ----------------------------------------- |
| GET     | `/statistics` | Statistiques générales de la bibliothèque |

### 🔐 Authentification

| Méthode | Endpoint         | Description                           |
| ------- | ---------------- | ------------------------------------- |
| POST    | `/auth/register` | Création d'un utilisateur             |
| POST    | `/auth/login`    | Authentification et génération du JWT |

---

## 🧪 Tests

Le projet intègre progressivement une stratégie de tests comprenant :

* **JUnit 5** pour les tests
* **Mockito** pour isoler les dépendances
* **Spring Boot Test** pour les tests d'intégration
* **Testcontainers** pour tester la persistance avec une véritable instance PostgreSQL

La stratégie de tests est actuellement en cours de développement.
