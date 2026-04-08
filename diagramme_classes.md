# Diagramme de classes

```mermaid
classDiagram
    namespace domain {
        class Plat {
            <<Entité>>
            #int id
            #String nom
            #String description
            #double prix
        }
        class Utilisateur {
            <<Entité>>
            #int id
            #String nom
            #String prenom
            #String email
            #String adresse
        }
    }

    namespace service {
        class PlatUseCaseInterface {
            <<interface>>
            +getAllPlatsJSON() String
            +getPlatJSON(int id) String
            +createPlat(Plat plat) Plat
            +updatePlat(int id, Plat plat) String
            +deletePlat(int id) boolean
        }
        class PlatService {
            #PlatRepositoryInterface platRepo
        }
        class PlatRepositoryInterface {
            <<interface>>
            +close()
            +getPlat(int id) Plat
            +getAllPlats() ArrayList~Plat~
            +createPlat(String nom, String description, double prix) Plat
            +updatePlat(int id, String nom, String description, double prix) boolean
            +deletePlat(int id) boolean
        }
        class UtilisateurUseCaseInterface {
            <<interface>>
            +getAllUtilisateursJSON() String
            +getUtilisateurJSON(int id) String
            +createUtilisateur(Utilisateur u) Utilisateur
            +updateUtilisateur(int id, Utilisateur u) String
            +deleteUtilisateur(int id) boolean
        }
        class UtilisateurService {
            #UtilisateurRepositoryInterface utilisateurRepo
        }
        class UtilisateurRepositoryInterface {
            <<interface>>
            +close()
            +getUtilisateur(int id) Utilisateur
            +getAllUtilisateurs() ArrayList~Utilisateur~
            +createUtilisateur(String nom, String prenom, String email, String adresse) Utilisateur
            +updateUtilisateur(int id, String nom, String prenom, String email, String adresse) boolean
            +deleteUtilisateur(int id) boolean
        }
    }

    namespace ui {
        class PlatResource {
            -PlatUseCaseInterface service
        }
        class UtilisateurResource {
            -UtilisateurUseCaseInterface service
        }
    }

    namespace database {
        class PlatRepositoryMariadb {
            #Connection dbConnection
        }
        class UtilisateurRepositoryMariadb {
            #Connection dbConnection
        }
    }

    class PlatsUtilisateursApplication {
        +openDbConnection() Connection
        +getPlatService() PlatUseCaseInterface
        +getUtilisateurService() UtilisateurUseCaseInterface
    }

    PlatUseCaseInterface     <|..  PlatService                 : est un
    PlatResource             ..>   PlatUseCaseInterface        : utilise
    PlatService              ..>   PlatRepositoryInterface     : utilise
    PlatService              ..>   Plat                        : utilise
    PlatRepositoryInterface  <|..  PlatRepositoryMariadb       : est un
    PlatRepositoryMariadb    ..>   Plat                        : utilise

    UtilisateurUseCaseInterface     <|..  UtilisateurService                : est un
    UtilisateurResource             ..>   UtilisateurUseCaseInterface       : utilise
    UtilisateurService              ..>   UtilisateurRepositoryInterface    : utilise
    UtilisateurService              ..>   Utilisateur                       : utilise
    UtilisateurRepositoryInterface  <|..  UtilisateurRepositoryMariadb      : est un
    UtilisateurRepositoryMariadb    ..>   Utilisateur                       : utilise

    PlatsUtilisateursApplication ..> PlatRepositoryMariadb        : cree
    PlatsUtilisateursApplication ..> UtilisateurRepositoryMariadb : cree
    PlatsUtilisateursApplication ..> PlatService                  : cree
    PlatsUtilisateursApplication ..> UtilisateurService           : cree

    classDef entity    fill:#fffacd,stroke:#daa520,color:#000
    classDef usecase   fill:#ffe4e1,stroke:#cd5c5c,color:#000
    classDef adapter   fill:#f0fff0,stroke:#2e8b57,color:#000
    classDef framework fill:#b0c4de,stroke:#4682b4,color:#000

    class Plat:::entity
    class Utilisateur:::entity
    class PlatUseCaseInterface:::usecase
    class PlatService:::usecase
    class UtilisateurUseCaseInterface:::usecase
    class UtilisateurService:::usecase
    class PlatRepositoryInterface:::adapter
    class UtilisateurRepositoryInterface:::adapter
    class PlatResource:::adapter
    class UtilisateurResource:::adapter
    class PlatRepositoryMariadb:::framework
    class UtilisateurRepositoryMariadb:::framework
    class PlatsUtilisateursApplication:::framework
```

Les interfaces UseCase (PlatUseCaseInterface, UtilisateurUseCaseInterface) dans la couche ui ne sont pas obligatoires, on pourrait directement utiliser PlatService et UtilisateurService. On les a mises pour respecter le cours et avoir une architecture plus propre, comme ca si on change l'implementation du service, le controller n'est pas impacte.
