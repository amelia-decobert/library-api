# LIBRARY API

> **Projet en cours de développement...**

API REST développée avec **Java** & **Spring Boot**, permettant de gérer une bibliothèque.

Ce projet est réalisé, en toute autonomie, dans le cadre de mon apprentissage du développement Backend Java.
Il évolue progressivement afin d'intégrer les technologies et bonnes pratiques utilisées en entreprise
(Spring Boot, PostgreSQL, JPA, Spring Security, Docker, Tests, CI/CD).

## État du projet

| Fonctionnalité                    | Statut |
|-----------------------------------|:------:|
| Architecture Controller / Service |   ✅    |
| CRUD REST                         |   ✅    |
| DTO                               |   ✅    |
| Validation                        |   ✅    |
| Gestion centralisée des erreurs   |   ✅    |
| PostgreSQL                        |   ⏳    |
| Spring Data JPA                   |   ⏳    |
| Tests (JUnit / Mockito)           |   ⏳    |
| Spring Security                   |   ⏳    |
| JWT                               |   ⏳    |
| Swagger / OpenAPI                 |   ⏳    |
| Flyway                            |   ⏳    |
| Docker                            |   ⏳    |
| GitHub Actions                    |   ⏳    |

---

## Technologies utilisées

| Technologie           | Usage                       |
|-----------------------|-----------------------------|
| **Java 21**           | Langage                     |
| **Spring Boot**       | Framework principal         |
| **Spring Web**        | API REST                    |
| **Spring Validation** | Validation des requêtes     |
| **Lombok**            | Réduction du code répétitif |
| **Maven**             | Build                       |

## Prérequis

- Éditeur de code IntelliJ IDEA (recommandé)
- Java21+ installé
- Maven
- Postman (ou autre client HTTP pour tester l'API)

---

## Installation et lancement du projet

Cloner le repo et se déplacer dans le dossier projet
```bash
git clone https://github.com/<votre-nom>/library-api.git
cd library-api
```
Attendre la fin de la synchronisation des dépendances Maven

Ouvrir le fichier `LibraryApiApplication.java` et cliquer sur le bouton lecture situé en début de ligne, à côté de `public static void main(){}`;
ou sur le bouton lecture situé en haut de la fenêtre, à côté de "Current File"

L'application démarre par défaut sur :
http://localhost:8080

Pour vérifier le bon lancement de l'application
```bash
curl http://localhost:8080/health
```

---

## Architecture du projet

```
library-api/
├── pom.xml
├── src/main/java/com/example/library_api/
│   ├── controller/
│   │   ├── BookController.java
│   │   └── HealthController.java
│   ├── dto/
│   │   ├── BookRequest.java
│   │   └── BookResponse.java
│   ├── exception/
│   │   ├── BookNotFoundException.java
│   │   └── GlobalExceptionHandler.java
│   ├── mapper/
│   │   └── BookMapper.java
│   ├── model/
│   │   └── Book.java
│   ├── repository/
│   ├── service/
│   │   └── BookService.java
│   ├── LibraryApiApplication.java
└── src/main/resources/application.properties
```

---

## Liste des endpoints

| Méthode  | Endpoint      | Description             |
|----------|---------------|-------------------------|
| GET      | /books        | Liste des livres        |
| GET      | /books/{id}   | Détails d'un livre      |
| GET      | /books/search | Recherche d'un livre    |
| POST     | /books        | Création d'un livre     |
| PUT      | /books/{id}   | Modification d'un livre |
| DELETE   | /books/{id}   | Suppression d'un livre  |

