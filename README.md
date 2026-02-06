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
De onderstaande benodigdheden zijn nodig om deze applicatie te kunnen runnen. Zorg dat deze zijn geïnstalleerd voordat de installatiestappen worden opgevolgd.

- Een LTS-versie van Java (25 is gebruikt als runtime tijdens development)
- PostgreSQL 18.1
- pgAdmin 4
- Maven / Maven Wrapper (bijgeleverd)
- IntelliJ IDEA
- Postman (desktop app)
- Je favoriete browser (voor Swagger)
- Windows 11
- Internetverbinding (tijdens het clonen)
- Git

> **_NOTITIE_**: Een ander besturingssysteem of IDE zijn toegestaan, maar daar wordt geen rekening mee gehouden tijdens de installatiestappen.

> **TIP**: Tijdens de PostgreSQL-installatie moet een gebruikersnaam en wachtwoord worden opgegeven; onthoud deze goed. Deze zijn namelijk nodig om de applicatie te kunnen draaien.

## Installatie stappen

1. **Installeer alle benodigdheden**  
Zorg dat alle [benodigdheden](#benodigdheden) zijn geïnstalleerd.  

2. **Maak een nieuwe database aan**  
Open pgAdmin en login met de inloggegevens die tijdens de installatie van PostgreSQL zijn opgegeven.
Maak een nieuwe database aan door met de rechtermuisknop op Databases (in Servers > PostgreSQL 18) te klikken. Geef de database een naam en druk op "save".

3. **Clone de repository**  
Clone de source code naar de lokale machine via `git clone` of download het project op een andere manier.

4. **Maak een `.env`-bestand aan**  
Kopieer het bestand `.env.dist` naar de root van het project en hernoem het naar `.env`.

5. **Vul het `.env`-bestand met de juiste waarden**  
Hoewel environment variabelen normaal gesproken niet openbaar gedeeld worden, zijn ze in dit geval
toegevoegd zodat de applicatie correct kan functioneren tijdens de beoordeling. Voeg de onderstaande
waarden toe aan het zojuist aangemaakte `.env`-bestand:
   ```
   SERVER_PORT=8080
   SPRING_DATASOURCE_URL_HOST=localhost
   SPRING_DATASOURCE_URL_PORT=5432
   SPRING_DATASOURCE_URL_DATABASE_NAME=<naam van de database in stap 2>
   SPRING_DATASOURCE_USERNAME=<gebruikersnaam opgegeven tijdens de PostgreSQL installatie>
   SPRING_DATASOURCE_PASSWORD=<wachtwoord opgegeven tijdens de PostgreSQL installatie>
   ```
   
6. **Installeer en start Keycloak**  
Download link: https://github.com/keycloak/keycloak/releases/download/26.5.2/keycloak-26.5.2.zip  
Pak het zip-bestand uit in een locatie naar keuze. Navigeer in de terminal naar deze locatie en voer het volgende commando uit:  
    ```bash
    bin\kc.bat start-dev --http-port 9090
    ```

7. **Importeer het realm bestand**
Navigeer in de browser naar http://localhost:9090/ en login met je logingegevens of maak deze aan als dit de eerste keer is dat deze installatie van Keycloak wordt opgestart.
Ga naar "Manage Realms" en druk op "Create realm". Voeg in de pop-up het `/keycloak/dnd-app-realm.json` bestand toe als resource file en druk op "Create". 

8. **Installeer de benodigde dependencies**  
Open het project in IntelliJ IDEA via File > Open door in de pop-up naar het project te navigeren en de pom.xml te openen.
IntelliJ IDEA zal vragen hoe dit project geopend moet worden, open het project via "Open as Project".
Wacht totdat alle dependencies door Maven geïnstalleerd zijn of ga naar pom.xml en druk op Sync Maven Changes om Maven een duwtje te geven.

9. **Start de applicatie**  
Start de web-API vanuit de terminal in de root van het project met het volgende commando:
    ```bash
    ./mvnw spring-boot:run
    ```

> **_NOTITIE_**:  
> Om Swagger correct te laten werken **moet de web-API op poort 8080 draaien**. Dit is nodig vanwege de Swagger OAuth 2.0 authentication flow, die gebruikmaakt van de volgende redirect-URL:
> 
> `http://localhost:{port}/swagger-ui/oauth2-redirect.html`  
> 
> In principe is de poort in deze redirect-URL dynamisch en afhankelijk van de poort waarop de web-API wordt gehost.
> Omdat er echter ook een authentication gedraaid wordt (Keycloak), moeten toegestane redirect-URL’s expliciet worden geregistreerd op de client binnen een realm.
> 
> Keycloak ondersteunt geen wildcards in het midden van een stringwaarde. Daarom is de toegestane redirect-URL in Keycloak vast ingesteld op:
> 
> `http://localhost:8080/swagger-ui/oauth2-redirect.html`

> **_NOTITIE_**: Werkt het starten van Keycloak niet goed? Zorg dan dat Java's PATH variabel juist staat ingesteld.

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

Door de import van `dnd-app-realm.json` staan er standaard 3 gebruikers klaar om mee te testen: 

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