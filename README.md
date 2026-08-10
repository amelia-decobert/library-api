# 📚 Library API

> 🚧 **Projet en cours de développement**

API REST développée avec **Java 21** et **Spring Boot 4.1.0**, permettant de gérer une bibliothèque.

Ce projet est réalisé en autonomie dans le cadre de ma spécialisation en développement **Backend Java**.

L'objectif est de construire progressivement une API proche d'un projet professionnel en intégrant des technologies et bonnes pratiques couramment utilisées dans l'écosystème Java/Spring :

**Spring Boot · PostgreSQL · JPA/Hibernate · Spring Security · JWT · Flyway · Swagger/OpenAPI · Tests · Docker · CI/CD**

---

## 🎯 Objectifs du projet

Ce projet me permet de mettre en pratique :

* la conception d'une API REST ;
* l'architecture en couches avec Spring Boot ;
* la persistance avec JPA/Hibernate et PostgreSQL ;
* la validation des données ;
* la gestion centralisée des erreurs ;
* la pagination, le tri et la recherche ;
* la sécurisation d'une API avec Spring Security et JWT ;
* les tests unitaires et d'intégration ;
* l'utilisation de Testcontainers ;
* la gestion des migrations de base de données avec Flyway ;
* la documentation d'une API avec OpenAPI/Swagger ;
* la conteneurisation avec Docker ;
* l'automatisation avec CI/CD.

---

## 🚧 État du projet

| Fonctionnalité                                 | Statut |
|------------------------------------------------| :----: |
| Architecture Controller / Service / Repository |    ✅   |
| API REST                                       |    ✅   |
| CRUD                                           |    ✅   |
| DTO                                            |    ✅   |
| Validation                                     |    ✅   |
| Gestion centralisée des erreurs                |    ✅   |
| PostgreSQL                                     |    ✅   |
| Spring Data JPA / Hibernate                    |    ✅   |
| Spring Security                                |    ✅   |
| JWT                                            |    ✅   |
| Flyway                                         |    ✅   |
| Swagger / OpenAPI                              |    ✅   |
| Tests unitaires avec JUnit 5                   |   🟡   |
| Tests avec Mockito                             |   🟡   |
| Tests d'intégration                            |   🟡   |
| Testcontainers                                 |   🟡   |
| Docker / Docker Compose                        |    ⏳   |
| GitHub Actions / CI/CD                         |    ⏳   |

> 🟡 **En cours** — fonctionnalité commencée mais pas encore finalisée
> 
> ⏳ **À venir** — fonctionnalité prévue dans la roadmap

---

## 🛠️ Technologies utilisées

| Technologie             | Usage                                        |
|-------------------------| -------------------------------------------- |
| **Java 21**             | Langage                                      |
| **Spring Boot 4.1.0**   | Framework principal                          |
| **Spring Web**          | Création de l'API REST                       |
| **Spring Validation**   | Validation des données entrantes             |
| **Spring Security**     | Authentification et autorisation             |
| **JWT**                 | Authentification stateless                   |
| **Spring Data JPA**     | Persistance des données                      |
| **Hibernate**           | ORM                                          |
| **PostgreSQL**          | Base de données relationnelle                |
| **Flyway**              | Gestion des migrations de base de données    |
| **SpringDoc / OpenAPI** | Documentation de l'API                       |
| **JUnit 5**             | Tests unitaires et d'intégration             |
| **Mockito**             | Mocking et isolation des dépendances         |
| **Testcontainers**      | Tests avec une véritable instance PostgreSQL |
| **Lombok**              | Réduction du code répétitif                  |
| **Maven**               | Gestion des dépendances et build             |

---

## 📋 Prérequis

* Editeur de code IntelliJ IDEA (recommandé)
* Java 21+
* Maven
* PostgreSQL
* Docker
* Postman (ou autre client HTTP)

---

## 🚀 Installation et lancement

### 1. Cloner le projet

```bash
git clone https://github.com/<votre-nom>/library-api.git
cd library-api
```

### 2. Configurer PostgreSQL

Créer une base de données :

```sql
CREATE USER <username> WITH PASSWORD '<mot-de-passe>';
CREATE DATABASE library OWNER <username>;
```

Configurer ensuite les paramètres de connexion dans votre fichier de configuration local.

### 3. Lancer l'application

Depuis IntelliJ IDEA :

```bash
./mvnw spring-boot:run
```

L'API démarre par défaut sur :

```text
http://localhost:8080
```

### 4. Vérifier l'application

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
service/        → logique métier
```

La sécurité est organisée autour de Spring Security et JWT.

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

### Sécurité

* Spring Security
* Authentification JWT
* Protection des endpoints
* Authentification stateless

### Base de données

* PostgreSQL
* JPA / Hibernate
* Migrations versionnées avec Flyway

### Documentation

* Documentation OpenAPI
* Interface Swagger UI

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

* PostgreSQL ;
* Spring Data JPA ;
* Hibernate.

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

Swagger UI fournit une documentation interactive permettant notamment de :

* consulter les endpoints disponibles ;
* visualiser les paramètres et réponses ;
* tester les endpoints directement depuis l'interface ;
* utiliser l'authentification Bearer JWT.

Une fois l'application démarrée, l'interface Swagger est accessible à l'adresse :

**http://localhost:8080/swagger-ui/index.html**

> 💡 L'URL ci-dessus est accessible uniquement lorsque l'application est exécutée localement.

---

## 🔌 API

Les principaux endpoints disponibles sont présentés ci-dessous.

Pour consulter la documentation complète, les paramètres, les schémas de requêtes/réponses et tester l'API, utiliser **Swagger UI** :

**http://localhost:8080/swagger-ui/index.html**

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

---

## 🧪 Tests

Le projet intègre progressivement une stratégie de tests comprenant :

* **JUnit 5** pour les tests ;
* **Mockito** pour isoler les dépendances ;
* **Spring Boot Test** pour les tests d'intégration ;
* **Testcontainers** pour tester la persistance avec une véritable instance PostgreSQL.

La couverture des tests est actuellement en cours d'amélioration.
