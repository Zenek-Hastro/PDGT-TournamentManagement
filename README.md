# PDGT - Tournament Management
##### Sviluppatore: 
Nicolò Santini, mat. 292404

### Obiettivi:
L'obiettivo principale del Web Service sviluppato è quello di semplificare la gestione dei dati anagrafici degli atleti iscritti ad un organizzatore di tornei sportivi.
Nello specifico, l'api si compone di 5 funzioni principali, che permettono di:
>1. Inserire dati anagrafici degli atleti, inserendoli in un database fornito da Google Firebase;
>2. Ricercare anagrafica atleti per cognome iscritto; 
>3. Ricercare anagrafica atleti per nome squadra iscritto;
>4. Eliminare un iscritto una volta noto il suo ID; 
>5. Visualizzare l'intera lista di iscritti.

Per apprezzare al meglio le funzioni fornite, è stato necessario sviluppare anche due applicazioni client che usassero al meglio le funzioni.
La documentazione e il README delle applicazioni client sono disponibili [qui](https://github.com/nicosanti98/PDGT-TournamentManagement/blob/master/CLIENT/README.md "qui").

------------
## Architettura e scelte implementative
![Architettura Servizio](https://github.com/nicosanti98/PDGT-TournamentManagement/blob/master/img/apistruct.png "Architettura Servizio")

L'architettura è implementata come nello schema descritto dall'immagine qua sopra:
1. Server scritto in NodeJs + Express;
2. Database Firebase che comunica con il server mandando e ricevendo dati;
3. Client Programma multipiattaforma (scritto in JAVA) che interagisce con il server mandando e ricevendo dati;
4. Client Sito Web che interagisce con il server mandando dati.

I dati interscambiati tra le varie componenti dell'architettura sono scambiati in formato *JSON*. Le richieste di connessione al sever e a Firebase sono implementate facendo delle richieste Http utilizzando, sia per il server, che per i client, la libreria XMLHttpRequest. 

*Alcune funzioni, come descritto in seguito, sono utilizzabili solo previa autenticazione. Non è supportata la registrazione automatica, pertanto le credenziali di accesso devono essere create dallo sviluppatore.*

------------
### Tournament Management API
L'API sviluppata ha diversi metodi:

- `/info`: metodo **GET**, ritorna alcune info dell'API
- `/login`: metodo **GET**, ritorna 200 se l'utente è autenticato, 401 in caso contrario;
- `/tournament/players/number`: metodo **GET**, ritorna il numero di iscritti presenti nel database;
- `/tournament/players`: metodo **GET**, ritorna l'intera lista di iscritti presenti nel database;
- `/tournament/players/searchbyname`: metodo **GET**, ritorna il/gli iscritto/i con il cognome corrispondente a quello passato alla richiesta come parametro;
- `/tournament/players/searchbyteam`: metodo **GET**, ritorna il/gli iscritto/i con il nome della squadra corrispondente a quello passato alla richiesta come parametro;
- `/tournament/players/`: metodo **POST**, permette l'inserimento di un nuovo iscritto inserendo nel database i dati anagrafici passati per parametri alla richiesta;
- `/tournament/players/`: metodo **DELETE**, rimuove l'iscritto presente all'ID-esimo posto nel database, considerando l'ID passatogli come parametro della richiesta;


Una lista di metodi per poter essere testati, lo schema dell'API e ulteriori informazioni su di essi, sono resi disponibili, grazi al tool *Swagger* a questo indirizzo: 
[Schema API.](https://app.swaggerhub.com/apis-docs/nicosanti98/API/1.0 "Schema API.")


###### N.B. Il README delle applicazioni Client è disponibile a questo indirizzo: [LINK](https://github.com/nicosanti98/PDGT-TournamentManagement/blob/master/CLIENT/README.md "LINK")

-----------

### Servizi Esterni utlizzati
Si fa uso inoltre di API esterne, nello specifico vengono invocate le funzioni fornite da Google per interagire con il suo servizio Firebase e poter quindi manipolare i dati.
Nello specifico vengono utilizzati i dati di configurazione del database per poter collegare quest'ultimo al server e nel codice vengono usate le query fornite da Firebase per poter filtrare, ordinare e recuperare dati degli atleti contenenti in un certo figlio un certo valore.

------------

### Messa online del servizio
Il server è hostato su Heroku: ogni volta che si esegue un commit su GitHub, Heroku esegue un Deploy automatico, permettendo di avere un server sempre attivo in *Continuos Delivery*.
Il link al server è qui disponibile: [LINK](https://tournament-manage.herokuapp.com/ "LINK").

Per testare il funzionamento dei metodi dell'API basta sostituire dopo l'URL del server, la path del metodo corrispondente.

------------
