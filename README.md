# Plats & Utilisateurs — API REST

API REST Jakarta EE pour la gestion des plats et utilisateurs d'un service de repas.  
Architecture **Clean Architecture** (Robert C. Martin) — R4.01 Architecture Logicielle.

## Structure

```
domain/     Entités métier (Plat, Utilisateur)
service/    Use Cases + interfaces Gateway
ui/         Controllers REST JAX-RS (/api/plats, /api/utilisateurs)
database/   Accès MariaDB via JDBC
```

Voir [diagramme_classes.md](diagramme_classes.md) pour le diagramme UML complet.

## Configuration

Créer `src/main/resources/db.properties` :

```properties
db.url=jdbc:mariadb://serveur/base
db.user=utilisateur
db.password=motdepasse
```

## Build

```bash
mvn clean package
# → target/demo-1.0-SNAPSHOT.war  (déployer sur GlassFish 7)
```
