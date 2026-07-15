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
| PostgreSQL                        |   ✅    |
| Spring Data JPA                   |   ✅    |
| Tests (JUnit / Mockito)           |   ⏳    |
| Spring Security                   |   ⏳    |
| JWT                               |   ⏳    |
| Swagger / OpenAPI                 |   ⏳    |
| Flyway                            |   ⏳    |
| Docker                            |   ⏳    |
| GitHub Actions                    |   ⏳    |

---

## Technologies utilisées

| Technologie           | Usage                         |
|-----------------------|-------------------------------|
| **Java 21**           | Langage                       |
| **Spring Boot**       | Framework principal           |
| **Spring Web**        | API REST                      |
| **Spring Validation** | Validation des requêtes       |
| **Spring Data JPA**   | Persistence, ORM              |
| **PostgreSQL**        | Base de données relationnelle |
| **Lombok**            | Réduction du code répétitif   |
| **Maven**             | Build                         |

## Prérequis

- Éditeur de code IntelliJ IDEA (recommandé)
- Java21+
- Maven
- PostgreSQL
- Postman (ou autre client HTTP pour tester l'API)

---

## Installation et lancement du projet

- Cloner le repo et se déplacer dans le dossier projet
```bash
git clone https://github.com/<votre-nom>/library-api.git
cd library-api
```

- Installer et démarrer PostgreSQL

**macOS** (via Homebrew) :
```bash
brew install postgresql@18
brew services start postgresql
```

- Créer la base de données *library*
```bash
psql -U <username>
CREATE DATABASE library;
```
- Lancer l'application

Attendre la fin de la synchronisation des dépendances Maven

Ouvrir `LibraryApiApplication.java` et cliquer sur le bouton lecture situé en début de ligne, à côté de `public static void main(){}`;
ou sur le bouton lecture situé en haut de la fenêtre, à côté de "Current File"

L'application démarre par défaut sur :
http://localhost:8080

Pour vérifier le bon lancement de l'application
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

## Architecture du projet

```
library-api/
├── src/main/java/com/example/library_api/
│   ├── config/
│   │   └── DataInitializer.java
│   ├── controller/
│   │   ├── AuthorController.java
│   │   ├── BookController.java
│   │   ├── CategoryController.java
│   │   ├── HealthController.java
│   │   └── StatisticsController.java
│   ├── dto/
│   │   ├── AuthorRequest.java
│   │   ├── AuthorResponse.java
│   │   ├── BookRequest.java
│   │   ├── BookResponse.java
│   │   ├── CategoryRequest.java
│   │   ├── CategoryResponse.java
│   │   ├── PageResponse.java
│   │   └── StatisticsResponse.java
│   ├── exception/
│   │   ├── AuthorNotFoundException.java
│   │   ├── BookNotFoundException.java
│   │   ├── CategoryNotFoundException.java
│   │   ├── GlobalExceptionHandler.java
│   │   └── NotFoundException.java
│   ├── mapper/
│   │   ├── AuthorMapper.java
│   │   ├── BookMapper.java
│   │   └── CategoryMapper.java
│   ├── model/
│   │   ├── Author.java
│   │   ├── Book.java
│   │   └── Category.java
│   ├── repository/
│   │   ├── AuthorRepository.java
│   │   ├── BookRepository.java
│   │   └── CategoryRepository.java
│   ├── service/
│   │   ├── AuthorService.java
│   │   ├── BookService.java
│   │   ├── CategoryService.java
│   │   └── StatisticsService.java
│   └── LibraryApiApplication.java
├── src/main/resources/application.properties.example
└── pom.xml
```

---

## Liste des endpoints

### Livres

| Méthode   | Endpoint               | Description                                                 |
|-----------|------------------------|-------------------------------------------------------------|
| GET       | /books                 | Liste paginée des livres                                    |
| GET       | /books/{id}            | Détails d'un livre                                          |
| GET       | /books/author/{author} | Livres d'un auteur donné                                    |
| GET       | /books/recent          | Livres publiés après une année donnée                       |
| GET       | /books/search          | Recherche combinée par titre, auteur et/ou année d'un livre |
| POST      | /books                 | Création d'un livre                                         |
| PUT       | /books/{id}            | Modification d'un livre                                     |
| DELETE    | /books/{id}            | Suppression d'un livre                                      |

**+ Paramètres de requête** GET /books

| Paramètre | Type   |  Défaut  | Description                                                       |
|-----------|--------|:--------:|-------------------------------------------------------------------|
| page      | int    |    0     | Numéro de page                                                    |
| size      | int    |    10    | Nombre d'éléments par page                                        |
| sort      | string |    -     | Champ de tri (title ; publicationYear, desc) + tri multi-critères |

### Auteurs

| Méthode  | Endpoint            | Description          |
|----------|---------------------|----------------------|
| GET      | /authors            | Liste des auteurs    |
| GET      | /authors/{id}       | Détails d'un auteur  |
| GET      | /authors/{id}/books | Livres d'un auteur   |
| POST     | /authors            | Création d'un auteur |

### Catégories

| Méthode | Endpoint    | Description              |
|---------|-------------|--------------------------|
| GET     | /categories | Liste des catégories     |
| POST    | /categories | Création d'une catégorie |

### Statistiques

| Méthode | Endpoint    | Description                                  |
|---------|-------------|----------------------------------------------|
| GET     | /statistics | Nombre total de livres, auteurs & catégories |
