# API Plats et Utilisateurs

API REST Jakarta EE pour la gestion des plats et des utilisateurs d'un service de livraison de repas.

## Auteur

**Younes BENAHMED** - R4.01 Architecture Logicielle

## Architecture

Le projet suit une architecture en couches pour chaque ressource :

```
fr.univamu.iut.platsutilisateurs/
├── PlatsUtilisateursApplication.java       # Point d'entrée JAX-RS + producteurs CDI
├── plat/
│   ├── Plat.java                           # Entité
│   ├── PlatRepositoryInterface.java        # Interface d'accès aux données
│   ├── PlatRepositoryMariadb.java          # Implémentation JDBC MariaDB
│   ├── PlatService.java                    # Couche métier + conversion JSON
│   └── PlatResource.java                   # Endpoints REST (/api/plats)
└── utilisateur/
    ├── Utilisateur.java                    # Entité
    ├── UtilisateurRepositoryInterface.java # Interface d'accès aux données
    ├── UtilisateurRepositoryMariadb.java   # Implémentation JDBC MariaDB
    ├── UtilisateurService.java             # Couche métier + conversion JSON
    └── UtilisateurResource.java            # Endpoints REST (/api/utilisateurs)
```

## Endpoints

### Plats (`/api/plats`)

| Méthode | URL             | Description              | Code retour |
|---------|-----------------|--------------------------|-------------|
| GET     | `/api/plats`    | Lister tous les plats    | 200         |
| GET     | `/api/plats/{id}` | Obtenir un plat        | 200 / 404   |
| POST    | `/api/plats`    | Créer un plat            | 201         |
| PUT     | `/api/plats/{id}` | Modifier un plat       | 200 / 404   |
| DELETE  | `/api/plats/{id}` | Supprimer un plat      | 204 / 404   |

### Utilisateurs (`/api/utilisateurs`)

| Méthode | URL                    | Description                  | Code retour |
|---------|------------------------|------------------------------|-------------|
| GET     | `/api/utilisateurs`    | Lister tous les utilisateurs | 200         |
| GET     | `/api/utilisateurs/{id}` | Obtenir un utilisateur     | 200 / 404   |
| POST    | `/api/utilisateurs`    | Créer un utilisateur         | 201         |
| PUT     | `/api/utilisateurs/{id}` | Modifier un utilisateur    | 200 / 404   |
| DELETE  | `/api/utilisateurs/{id}` | Supprimer un utilisateur   | 204 / 404   |

## Stack technique

- Java 21
- Jakarta EE (JAX-RS, CDI, JSON-B)
- MariaDB (JDBC)
- GlassFish 7
- Maven

## Configuration

Créer le fichier `src/main/resources/db.properties` :

```properties
db.url=jdbc:mariadb://votre-serveur/votre-base
db.user=votre-utilisateur
db.password=votre-mot-de-passe
```

## Build et déploiement

```bash
mvn clean package
```

Le fichier WAR généré se trouve dans `target/demo-1.0-SNAPSHOT.war`.
