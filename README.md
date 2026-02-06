# Dungeons & Dragons web-API
This repository holds the source code of a web-API that automates tedious and cumbersome Dungeon & Dragons administrative tasks.
It was developed as the final project for my Backend class. This brief introduction is written in English to provide some context,
but all subsequent chapters will be written in Dutch as required for my assessment. Feel free to whip out your favorite translator app!

## Inhoudsopgave

- [Inleiding](#inleiding)
- [Projectstructuur](#projectstructuur)
- [Technieken & frameworks](#technieken--frameworks)
- [Benodigdheden](#benodigdheden)
- [Installatie stappen](#installatie-stappen)
- [Testen](#testen)
  - [Testdata](#testdata)
  - [Gebruikers & rollen](#gebruikers--rollen)
  - [Handmatig testen](#handmatig-testen)
    - [Postman](#postman)
    - [Swagger](#swagger)
  - [Geautomatiseerde testen](#geautomatiseerde-testen)
    - [Integratietests](#integratietests)
    - [Unit-tests](#unit-tests)

## Inleiding

De Dungeons & Dragons web-API automatiseert de vertragende elementen van de analoge vorm van Dungeons & Dragons.
Hiermee kan een frontend-applicatie deze processen via een gebruikersvriendelijke interface automatiseren.
De frontend-applicatie zelf valt buiten de scope van dit project. De belangrijkste functionaliteiten zijn als volgt:

- Inloggen en registreren
  - Om de Dungeons & Dragons web-API te gebruiken, moet de gebruiker geauthenticeerd zijn.
  - Zowel registratie als login verloopt via een aparte authenticatieserver (Keycloak).
- Personagebeheer
  - Creëer en beheer zelf gemaakte personages (characters) via de `/characters` endpoints (CRUD).
  - Vraag op of upload het portret van een personage via de `/characters/{id}/portrait` endpoints.
  - Beheer de inventaris van een personage door referenties te leggen tussen bestaande resources (equipment & weapons) of zelfs custom items tijdens POST- en PUT-verzoeken naar de `/character` endpoints.
  - Personage mutaties kunnen alleen gedaan worden door de gebruiker die het personage heeft aangemaakt.
- Tegenkomstenbeheer
  - Creëer en beheer tegenkomsten (encounters) via de `/encounters` endpoints (CRU).
  - Maak deelneemverzoeken aan en keur deze goed of af via de `/encounters/{id}/join-request` endpoints (CRU).
  - Tegenkomst mutaties kunnen alleen gedaan worden door gebruiker die de tegenkomst heeft aangemaakt.
- Spelinformatiebeheer
  - Creëer en beheer uitrusting (equipment) en wapens (weapons) via de `/equipment` & `/weapons` endpoints (CRUD).
  - Er kan naar deze resources verwezen worden vanuit de inventaris van een personage.

> **_NOTITIE_**: Hierboven zijn de Nederlandse termen voor character, encounters, equipment en weapons aangehouden zoals vermeld in het technisch ontwerp.
> Vanaf nu wordt de Engelse term gebruikt om de verbinding met de code beter te kunnen maken.

## Projectstructuur
Dit project gebruikt... controller > service > repository (duh)
DTO's, business models, entities
<details>

<summary>Mappenstructuur</summary>

```
├───.idea
├───.mvn
│   └───wrapper
├───documentation
│   └───sequence-diagrams
├───keycloak
├───src
│   ├───main
│   │   ├───java
│   │   │   └───walter
│   │   │       └───duncan
│   │   │           └───dndwebapi
│   │   │               ├───businessmodels
│   │   │               │   ├───charactermanagement
│   │   │               │   │   └───inventory
│   │   │               │   ├───encountermanagement
│   │   │               │   └───gameinformation
│   │   │               ├───config
│   │   │               │   └───openapi
│   │   │               │       ├───annotations
│   │   │               │       │   ├───charactermanagement
│   │   │               │       │   ├───encountermanagement
│   │   │               │       │   └───gameinformation
│   │   │               │       └───examples
│   │   │               │           ├───charactermanagement
│   │   │               │           ├───encountermanagement
│   │   │               │           └───gameinformation
│   │   │               ├───controllers
│   │   │               │   ├───charactermanagement
│   │   │               │   ├───encountermanagement
│   │   │               │   └───gameinformation
│   │   │               ├───dtos
│   │   │               │   ├───charactermanagement
│   │   │               │   │   └───inventory
│   │   │               │   ├───encountermanagement
│   │   │               │   └───gameinformation
│   │   │               │       ├───equipment
│   │   │               │       └───weapon
│   │   │               ├───entities
│   │   │               │   ├───charactermanagement
│   │   │               │   │   └───inventory
│   │   │               │   ├───encountermanagement
│   │   │               │   ├───gameinformation
│   │   │               │   └───usermanagement
│   │   │               ├───exceptions
│   │   │               ├───helpers
│   │   │               ├───mappers
│   │   │               │   ├───charactermanagement
│   │   │               │   │   └───inventory
│   │   │               │   ├───encountermanagement
│   │   │               │   └───gameinformation
│   │   │               │       ├───equipment
│   │   │               │       └───weapon
│   │   │               ├───repositories
│   │   │               │   ├───charactermanagement
│   │   │               │   ├───encountermanagement
│   │   │               │   ├───gameinformation
│   │   │               │   └───usermanagement
│   │   │               └───services
│   │   │                   ├───charactermanagement
│   │   │                   │   └───factories
│   │   │                   ├───encountermanagement
│   │   │                   ├───filemanagement
│   │   │                   ├───gameinformation
│   │   │                   └───usermanagement
│   │   └───resources
│   │       └───character-portraits
│   └───test
│       └───java
│           └───walter
│               └───duncan
│                   └───dndwebapi
│                       ├───controllers
│                       └───services
└───uploads
```
</details>

## Technieken & frameworks
Keycloak
Spring Boot 4.0
JUnit

## Benodigdheden
Windows
Java (versie)
Postgresql (versie)
Git
Maven Wrapper (bijgeleverd)
Internetverbinding (tijdens clonen)

Postman (testen)

## Installatie stappen
1. Zorg dat alle 
## Testen
### Testdata
Om direct relevante handmatige tests uit te kunnen voeren wordt de database bij iedere start up van de applicatie gevuld met standaard gegevens.
Deze gegevens staan gedefinieerd in `/src/main/resources/dnd-web-api.sql`. Character portraits komen op de volgende plek te staan: `/uploads`.
De folder waar character portraits naar worden geüpload wordt niet meegenomen in het versiebeheersysteem.
De methode `.../services/filemanagement/FileSystemStorageService.init()` zorgt ervoor dat bij iedere start up de afbeelding
uit `/src/main/resources/character-portraits/Gandalf_the_Grey.gif` wordt gekopieerd naar de `/uploads` folder.
Deze afbeelding is gekoppeld aan het character met id = 1. Bij iedere start up worden de database en de upload folder leeg gemaakt en opnieuw gevuld.
De volgende testdata is bij iedere start up beschikbaar:

* 5x Weapons
* 5x Equipment
* 4x Character types
* 5x Character races
* 5x Character classes
* 5x Characters
  * Gekoppeld aan player1
* 6x Character inventory items
  * Gekoppeld aan character met id = 1
  * 3 weapons, 2 equipment en 1 custom
* 1x Character portrait
  * Gekoppeld aan character met id = 1
* 1x Encounter
  * Gekoppeld aan dungeon-master1
* 3x Encounter participants
  * Gekoppeld aan encounter met id = 1
* 5x Encounter join requests
  * Gekoppeld aan encounter met id = 1
  * 3 approved, 1 declined, 1 pending

### Gebruikers & rollen
De applicatie kent 3 verschillende rollen, ieder moet hun eigen bevoegdheden.

| Rol            | Bevoegdheid                                                                                                                                                                                                                                   |
|----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Player         | Characters beheren (CRUD), Encounters ophalen, Join Requests aan Encounters doen, Equipment- en Weapon-informatie ophalen                                                                                                                     |
| Dungeon master | Characters beheren (CRUD), Encounters beheren (ophalen, aanmaken, participant toevoegen, starten, volgende beurt geven, sluiten), Join Request van Encounters beheren (ophalen en goed- of afkeuren), Equipment- en Weapon-informatie ophalen |
| Admin          | Game information beheren (CRUD)                                                                                                                                                                                                               |

Door de import van `dnd-app-realm.json` staan er standaard 3 gebruiker klaar om mee te testen: 

| Gebruikersnaam  | Wachtwoord      | Toegewezen rol(len)           | Opmerking                                           |
|-----------------|-----------------|-------------------------------|-----------------------------------------------------|
| player1         | player1         | PLAYER                        | Heeft 5 characters klaar staan (id = 1, 2, 3, 4, 5) |
| dungeon-master1 | dungeon-master1 | PLAYER, DUNGEON_MASTER        | Heeft 1 encounter klaar staan (id = 1)              |
| admin1          | admin1          | PLAYER, DUNGEON_MASTER, ADMIN | Kan alles (binnen de business rules 😉)             |

### Handmatig testen
Dit project ondersteund officieel twee verschillende manier om handmatig te testen. Hieronder wordt beschreven
hoe Postman en Swagger gebruikt kunnen worden om handmatig te testen.

#### Postman
...uitleg over hoe postman gebruikt kan worden...

#### Swagger
...uitleg over hoe swagger gebruikt kan worden en waar op gelet moet worden...

### Geautomatiseerde testen
Deze applicatie bevat geen line coverage van 100% over alle files. Maar het bevat wel...
#### Integratietests

#### Unit-tests