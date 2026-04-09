# Diagramme de classes

```plantuml
@startuml diagramme_classes

skinparam classAttributeIconSize 0
skinparam packageStyle rectangle
skinparam shadowing false

skinparam class {
    BackgroundColor<<entity>>    LightYellow
    BorderColor<<entity>>        Goldenrod
    BackgroundColor<<usecase>>   MistyRose
    BorderColor<<usecase>>       IndianRed
    BackgroundColor<<adapter>>   Honeydew
    BorderColor<<adapter>>       SeaGreen
    BackgroundColor<<framework>> LightSteelBlue
    BorderColor<<framework>>     SteelBlue
}

skinparam interface {
    BackgroundColor<<usecase>>  MistyRose
    BorderColor<<usecase>>      IndianRed
    BackgroundColor<<adapter>>  Honeydew
    BorderColor<<adapter>>      SeaGreen
}

package "domain" #LightYellow {
    class Plat <<entity>> {
        # id : int
        # nom : String
        # description : String
        # prix : double
    }
    class Utilisateur <<entity>> {
        # id : int
        # nom : String
        # prenom : String
        # email : String
        # adresse : String
    }
}

package "service" #MistyRose {
    interface PlatUseCaseInterface <<I>> <<usecase>> {
        + getAllPlatsJSON() : String
        + getPlatJSON(id : int) : String
        + createPlat(plat : Plat) : Plat
        + updatePlat(id : int, plat : Plat) : String
        + deletePlat(id : int) : boolean
    }
    class PlatService <<usecase>> {
        # platRepo : PlatRepositoryInterface
    }
    interface PlatRepositoryInterface <<I>> <<usecase>> {
        + close()
        + getPlat(id : int) : Plat
        + getAllPlats() : ArrayList<Plat>
        + createPlat(...) : Plat
        + updatePlat(...) : boolean
        + deletePlat(id : int) : boolean
    }
    interface UtilisateurUseCaseInterface <<I>> <<usecase>> {
        + getAllUtilisateursJSON() : String
        + getUtilisateurJSON(id : int) : String
        + createUtilisateur(u : Utilisateur) : Utilisateur
        + updateUtilisateur(id : int, u : Utilisateur) : String
        + deleteUtilisateur(id : int) : boolean
    }
    class UtilisateurService <<usecase>> {
        # utilisateurRepo : UtilisateurRepositoryInterface
    }
    interface UtilisateurRepositoryInterface <<I>> <<usecase>> {
        + close()
        + getUtilisateur(id : int) : Utilisateur
        + getAllUtilisateurs() : ArrayList<Utilisateur>
        + createUtilisateur(...) : Utilisateur
        + updateUtilisateur(...) : boolean
        + deleteUtilisateur(id : int) : boolean
    }
}

package "ui" #Honeydew {
    class PlatResource <<adapter>> {
        - service : PlatUseCaseInterface
    }
    class UtilisateurResource <<adapter>> {
        - service : UtilisateurUseCaseInterface
    }
}

package "database" #LightSteelBlue {
    class PlatRepositoryMariadb <<framework>> {
        # dbConnection : Connection
    }
    class UtilisateurRepositoryMariadb <<framework>> {
        # dbConnection : Connection
    }
}

class PlatsUtilisateursApplication <<framework>> #LightSteelBlue {
    - openDbConnection() : Connection
    - closeDbConnection(connection : Connection)
    - getPlatRepository(connection : Connection) : PlatRepositoryInterface
    - getUtilisateurRepository(connection : Connection) : UtilisateurRepositoryInterface
    - getPlatService(platRepo : PlatRepositoryInterface) : PlatUseCaseInterface
    - getUtilisateurService(utilisateurRepo : UtilisateurRepositoryInterface) : UtilisateurUseCaseInterface
}

' ── Relations Plat ──────────────────────────────────────

PlatResource          ..>  PlatUseCaseInterface     : utilise
PlatService           ..|> PlatUseCaseInterface     : est un
PlatService           ..>  PlatRepositoryInterface  : utilise
PlatService           ..>  Plat                     : utilise
PlatRepositoryMariadb ..|> PlatRepositoryInterface  : est un
PlatRepositoryMariadb ..>  Plat                     : utilise

' ── Relations Utilisateur ───────────────────────────────

UtilisateurResource          ..>  UtilisateurUseCaseInterface    : utilise
UtilisateurService           ..|> UtilisateurUseCaseInterface    : est un
UtilisateurService           ..>  UtilisateurRepositoryInterface : utilise
UtilisateurService           ..>  Utilisateur                    : utilise
UtilisateurRepositoryMariadb ..|> UtilisateurRepositoryInterface : est un
UtilisateurRepositoryMariadb ..>  Utilisateur                    : utilise

' ── Main ────────────────────────────────────────────────

PlatsUtilisateursApplication ..> PlatRepositoryMariadb         : cree
PlatsUtilisateursApplication ..> UtilisateurRepositoryMariadb  : cree
PlatsUtilisateursApplication ..> PlatService                   : cree
PlatsUtilisateursApplication ..> UtilisateurService            : cree

@enduml
```

Les interfaces UseCase (PlatUseCaseInterface, UtilisateurUseCaseInterface) dans la couche ui ne sont pas obligatoires, on pourrait directement utiliser PlatService et UtilisateurService. On les a mises pour respecter le cours et avoir une architecture plus propre, comme ca si on change l'implementation du service, le controller n'est pas impacte.
