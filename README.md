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
- [Overige toelichting](#overige-toelichting)

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
Dit project maakt gebruik van een **gelaagde architectuur**. Controllers zijn het start- en eindpunt van een verzoek naar de web-API.
Zij definiëren de endpoints en hoe een verzoek eruit moet zien. Controller werken samen met services, waarin businesslogica leeft
en alle business rules worden gevalideerd.

Om de Separation of Concerns (SOLID-principe "S") te respecteren, is er een duidelijke scheiding tussen DTO's, business models en entities:
- DTO's verzorgen de communicatie tussen de client en de web-API.
- Business models bevatten de businesslogica en de meeste business rule validations, inclusief berekende velden o.b.v. businesslogica.
- Entities representeren de database-objecten, relaties en constraints van data.

### Doorsnee POST-request flow
Een typisch POST request doorloopt in dit project de volgende stappen:
1. De controller ontvangt het verzoek van de client.
2. Jackson mapt de JSON-requestbody naar een request DTO en valideer de velden.
3. De controller stuurt het request DTO door naar de service.
4. De service zet het request DTO om naar een business model en voert validaties uit volgens de business rules.
5. Extra validaties die niet in het business model passen worden door de service uitgevoerd.
6. De service mapt het business model naar een entity.
7. De repository slaat de entity op via Hibernate (ORM) en retourneert de opgeslagen entity.
8. De service zet het entity terug naar een business model en stuurt dit naar de controller.
9. De controller mapt het busines model naar een response DTO met de berekende velden.
10. Jackson zet het response DTO om naar een JSON-responsebody en de client ontvangt het antwoord.

### Domeinen en mappenstructuur
Binnen dit project, en de mappenstructuur, is er een duidelijke splitsing tussen verschillende domeinen.
Doordat er veel verschillende bestanden zijn, wordt hier ook onderscheid gemaakt in de mappenstructuur. De domeinen zijn:
- Character management
- Game information
- Encounter management

Bekijk hieronder de volledige mappenstructuur:
<details>

<summary>Mappenstructuur</summary>

```
├───.idea
├───.mvn
│   └───wrapper
├───.documentation
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
De volgende technieken en frameworks zijn gebruikt om de web-API te realiseren:

- Spring Boot 4.0 voor het bouwen van de web-API.
- PostgreSQL 18.1 voor de relationele database voor opslag.
- Keycloak voor Identity & Access Management (OAuth2/OpenID Connect).
- Postman & Swagger voor API-documentatie en endpoint-testing.
- JUnit voor integratietests en unit-tests.

## Benodigdheden
De onderstaande benodigdheden zijn nodig om deze applicatie te kunnen runnen. Zorg dat deze zijn geïnstalleerd voordat de installatiestappen worden opgevolgd.

| Benodigdheid       | Versie  | Opmerking                                  |
|--------------------|---------|--------------------------------------------|
| Java SDK           | LTS     | Java 25 is gebruikt tijdens development    |
| PostgreSQL         | 18.1    | -                                          |
| pgAdmin            | 4       | -                                          |
| Maven              | 3.9.11  | Optioneel, Maven wrapper heeft de voorkeur |
| Maven Wrapper      | -       | Wordt bijgeleverd in dit project           |
| IntelliJ IDEA      | -       | -                                          |
| Postman            | 11.84.0 | -                                          |
| Windows 11         | -       | -                                          |
| Internetverbinding | -       | Alleen nodig tijdens het clonen            |
| Git                | -       | -                                          |

> **_NOTITIE_**: Een ander besturingssysteem of IDE zijn toegestaan, maar daar wordt geen rekening mee gehouden tijdens de installatiestappen.

> **TIP**: Tijdens de PostgreSQL-installatie moet een gebruikersnaam en wachtwoord worden opgegeven; onthoud deze goed. Deze zijn namelijk nodig om de applicatie te kunnen draaien.
> Zijn deze vergeten? Dan moet PostgreSQL opnieuw geïnstalleerd worden.

## Installatie stappen

1. **Installeer alle benodigdheden**  
Zorg dat alle [benodigdheden](#benodigdheden) zijn geïnstalleerd voordat je verdergaat. Deze installatiestappen zijn specifiek voor
dit project en beschrijven niet hoe elke dependency afzonderlijk geïnstalleerd moet worden.

2. **Maak een nieuwe database aan**  
Open pgAdmin en login met de inloggegevens die tijdens de installatie van PostgreSQL zijn opgegeven.
Maak een nieuwe database aan door met de rechtermuisknop op Databases (in Servers > PostgreSQL 18) te klikken.
Geef de database een naam, onthoud deze, en druk op "save".

3. **Clone de repository**  
Clone de source code naar de lokale machine via `git clone` of download het project op een andere manier.

4. **Maak een `.env`-bestand aan**  
Kopieer het bestand `.env.dist` uit de root van dit project naar de root van dit project en hernoem het naar `.env`.
Er staan nu twee env-bestanden, `.env` en `env.dist`.

5. **Vul het `.env`-bestand met de juiste waarden**  
Hoewel environment variabelen normaal gesproken niet openbaar gedeeld worden, zijn ze in dit geval
toegevoegd zodat de applicatie correct kan functioneren tijdens de beoordeling. Voeg de onderstaande
waarden toe aan het zojuist aangemaakte `.env`-bestand en vul de waarden tussen de haakjes in:
   ```
   SERVER_PORT=8080
   SPRING_DATASOURCE_URL_HOST=localhost
   SPRING_DATASOURCE_URL_PORT=<poort die is opgegeven tijdens de PostgreSQL installatie, standaard: 5432>
   SPRING_DATASOURCE_URL_DATABASE_NAME=<naam van de database in stap 2>
   SPRING_DATASOURCE_USERNAME=<gebruikersnaam die is opgegeven tijdens de PostgreSQL installatie>
   SPRING_DATASOURCE_PASSWORD=<wachtwoord die is opgegeven tijdens de PostgreSQL installatie>
   ```
   
6. **Installeer en start Keycloak**  
Download link: https://github.com/keycloak/keycloak/releases/download/26.5.2/keycloak-26.5.2.zip  
Pak het zip-bestand uit in een locatie naar keuze. Navigeer in de terminal naar deze locatie en voer het volgende commando uit
op dezelfde locatie waarin de bin-folder zich bevindt:  
    ```bash
    bin\kc.bat start-dev --http-port 9090
    ```

7. **Importeer het realm bestand**
Navigeer in de browser naar http://localhost:9090/ wanneer Keycloak draait en login met je logingegevens of maak deze aan als dit de eerste keer is dat deze installatie van Keycloak wordt opgestart.
Ga naar "Manage Realms" en druk op "Create realm". Voeg in de pop-up het bestand `/keycloak/dnd-app-realm.json` uit dit project toe als resource file en druk op "Create". 

8. **Start de applicatie**  
Start de web-API vanuit de terminal in de root van het project met het volgende commando (afhankelijk van OS):
    ```bash
    .\mvnw spring-boot:run
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
Het is mogelijk om een account te registreren via het Keycloak loginscherm, druk daar op registeren en maak een account aan. Het account krijgt standaard de PLAYER role.
Dungeon master en admin rollen worden door de beheerder van de applicatie toegewezen via Keycloak. Zelf registreren als dungeon master of admin is niet mogelijk.

De applicatie kent 3 verschillende rollen, ieder moet hun eigen bevoegdheden.

| Rol            | Bevoegdheid                                                                                                                                                                                                                                   |
|----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Player         | Characters beheren (CRUD), Encounters ophalen, Join Requests aan Encounters doen, Equipment- en Weapon-informatie ophalen                                                                                                                     |
| Dungeon master | Characters beheren (CRUD), Encounters beheren (ophalen, aanmaken, participant toevoegen, starten, volgende beurt geven, sluiten), Join Request van Encounters beheren (ophalen en goed- of afkeuren), Equipment- en Weapon-informatie ophalen |
| Admin          | Game information beheren (CRUD)                                                                                                                                                                                                               |

Door de import van `dnd-app-realm.json` staan er standaard 6 gebruikers klaar om mee te testen: 

| Gebruikersnaam  | Wachtwoord      | Toegewezen rol(len)           | Opmerking                                           |
|-----------------|-----------------|-------------------------------|-----------------------------------------------------|
| player1         | player1         | PLAYER                        | Heeft 5 characters klaar staan (id = 1, 2, 3, 4, 5) |
| dungeon-master1 | dungeon-master1 | PLAYER, DUNGEON_MASTER        | Heeft 1 encounter klaar staan (id = 1)              |
| admin1          | admin1          | PLAYER, DUNGEON_MASTER, ADMIN | Kan alles (binnen de business rules 😉)             |
| player2         | player2         | PLAYER                        | Heeft geen data                                     |
| dungeon-master2 | dungeon-master2 | PLAYER, DUNGEON_MASTER        | Heeft geen data                                     |
| admin2          | admin2          | PLAYER, DUNGEON_MASTER, ADMIN | Kan alles (binnen de business rules 😉)             |

### Handmatig testen
Dit project ondersteund officieel twee manieren om handmatig te testen.
Hieronder wordt beschreven hoe Postman en Swagger gebruikt kunnen worden om handmatig te testen.

> **_NOTITIE_**: Binnen de applicatie zijn veel business rules van kracht. Hierdoor is het eenvoudig om een request te versturen dat door de web-API wordt geweigerd.
> Daarnaast zijn bepaalde resources, zoals characters en encounters, alleen toegankelijk onder specifieke voorwaarden (bijvoorbeeld op basis van autorisatie of geldige data)

#### Postman
Voor handmatig testen is een Postman-collectie beschikbaar met vooraf ingestelde requests die eenvoudig aan te passen zijn.
Importeer `/src/main/resources/DnD web-API.postman_collection.json` in de Postman desktop app.

De security is binnen Postman geregeld via de Authorization-tab van de geïmporteerde collectie.
- Klik op de geïmporteerde collectie
- Klik in het detailscherm op de Authorization-tab
- Scroll naar benden en druk op "Get New Access Token"
- Login met een van de accounts uit het [Gebruikers & Rollen](#gebruikers--rollen) hoofdstuk.

> **_NOTITIE_**: Standaard vewijst de baseUrl naar http://localhost:8080 en is de authenticatie afgestemd op poort 9090.
> Stel de collectie variabelen in indien dat nodig is.

#### Swagger
Swagger kan ook worden gebruikt om handmatig te testen, maar is voornamelijk bedoeld voor de documentatie van de web-API.
Om Swagger te gebruiken moeten de web-API en Keycloak draaien. Navigeer naar `http://localhost:8080/swagger-ui/index.html` terwijl de web-API en Keycloak draaien.

De security is binnen Swagger geregeld met een geconfigureerde OAuth2.0 flow.
Druk op de Swagger-pagina rechtsboven op "Authorize". De client_id en client_secret zijn al ingevuld, druk nogmaals op "Authorize".
Log nu bij Keycloak in met een van de accounts uit het [Gebruikers & Rollen](#gebruikers--rollen) hoofdstuk.

Voor het gemak hebben de volgende requests een voorbeeld request body geconfigureerd (you're welcome 😉):
- POST /characters
- PUT /characters/{id}
- POST /encounters
- POST /equipment
- PUT /equipment/{id}
- POST /weapons
- PUT /weapons/{id}

> **_NOTITIE_**: Het is niet mogelijk om via Swagger een character portrait te uploaden, wel om er een op te halen.
> Dit is wel mogelijk met Postman of andere verzoeken zoals via de browser zelf in een frontend-applicatie. Dit is een configuratie pijnpunt van Swagger.

### Geautomatiseerde testen
Deze applicatie bevat 100% line coverage over alle bestanden. Wel is er een line coverage van 100% behaald voor twee controllers (integratietests) en twee services (unit-tests)
De WeaponController, EncounterController, WeaponService en EncounterService hebben allemaal een line coverage van 100%.

De testen zijn bewust minder DRY geschreven om scenario's expliciet en duidelijk te houden. In dit geval is dat een feature,
de flexibiliteit en onderhoudbaarheid van individuele scenario's blijven hierdoor hoog en overzichtelijk.

De unit- en integratietesten kunnen met één commando vanuit de root van dit project gedraaid uitgevoerd:

```bash
.\mvnw clean verify "-Dspring.profiles.active=test"
```

Het is belangrijk om het argument `-Dspring.profiles.active=test` mee te geven. Zonder dit argument wordt het testprofiel niet gebruikt
en zal Spring het productieprofiel `application.properties` gebruiken i.p.v. `application-test.properties`.

Aan het einde van de testrun wordt een rapportage in de terminal weergegeven, bijvoorbeeld:

> [INFO] Results:  
> [INFO]   
> [INFO] Tests run: 49, Failures: 0, Errors: 0, Skipped: 0

#### Integratietests
De integratietesten draaien tegen een H2 in-memory database. De testdata is voor iedere test gelijk en wordt geladen vanuit `dnd-web-api-test.sql`. 
Tijdens deze testen wordt de volledige Spring context geladen om de samenwerking tussen alle componenten te testen.

#### Unit-tests
De unit-tests maken gebruik van mock-objecten voor request DTO's, business models en entities. Alleen de nodige properties om een "unit" te testen krijgen een stub met een waarde.
Dit voorkomt onnodige overhead en zorgt ervoor dat de focus van de test ligt op het gedrag van de afzonderlijke unit.

### Overige toelichting
De volgende entiteiten bestaan puur om het character gedeelte van de applicatie netjes op te splitsen: CharacterType, CharacterRace, CharacterClass.
Er zijn voor deze entiteiten testgegevens beschikbaar. Hier moet naar worden verwezen tijdens het aanmaken en updaten van een Character (typeId, raceId & classId).
Momenteel zijn er geen CRUD-acties voor deze entiteiten. De volgende id's zijn valide:

| Entiteit       | Id's          |
|----------------|---------------|
| CharacterType  | 1, 2, 3, 4    |
| CharacterRace  | 1, 2, 3, 4, 5 |
| CharacterClass | 1, 2, 3, 4, 5 |
