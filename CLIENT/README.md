# PDGT - Tournament Management Applicazioni Client
##### Sviluppatore: 
Nicolò Santini, mat. 292404

### Obiettivi:
L'obiettivo delle applicazioni Client è quello di sfruttare al meglio i metodi offerti dall'API, semplificando l'utilizzo e rendendo il Web Service realmente utile.

------------
## Architettura e scelte implementative
Il progetto si compone di due applicazioni Client. 
- La prima un programma desktop multipiattaforma sviluppato in JAVA con interfaccia grafica Swing pensato per gli organizzatori del torneo che permette l'interazione completa con il database come se si operasse direttamente nella WebApp di Firebase.
- La seconda un sito WEB scritto in HTML, con una pagina dedicata all'inserimento di giocatori direttamente dal sito mediante un modulo iscrizioni.

------------
### Tournament Management Programma Desktop

Il programma è composto da 5 classi, ognuna delle quali contiene un form:
>1. Kotc1
>2. Main
>3. Inserimento
>4. Modifica
>5. Visualizza

1. La classe **Kotc1** contiene il primo form che viene eseguito e consiste in una schermata di Login in cui l'admin dovrà inserire le proprie credenziali. Se errate verrà comunicato un messaggio di errore, altrimenti verrà aperto il form *Main*.
2. La classe **Main** contiene il secondo form che verrà eseguito. Esso contiene un menu in cui l'utente può scegliere se eseguire il form *Inserimento*, il form *Modifica* o il form *Visualizza*.
3. La classe **Inserimento** contiene il form *Inserimento* . Grazie ad esso l'utente può inserire un nuovo iscritto inserendo i dati anagrafici del nuovo iscritto. Lasciando i campi vuoti l'inserimento avrà comunque luogo, e al valore lasciato vuoto verrà visualizzata una stringa vuota. La sezione Inserimento per ID è fruibile dal solo sviluppatore (loggato mediante apposite credenziali diverse da quelle dell'organizzatore) in quanto tale operazione può risultare pericolosa e generare errori se non usata correttamente.
4. La classe **Modifica** contiene il form *Modifica*: In esso viene visualizzata la lista di iscritti presenti nel database, se l'utente seleziona un elemento in questa lista ha la possibilità di modificarne i dati anagrafici e di aggiornarli nel database. Analogamente è possibile eliminare i dati di un iscritto. Cliccando il bottone "Elimina" dopo aver selezionato un atleta è possibile eliminarlo dal database. Il form offre inoltre due strumenti per filtrare la lista cercando un atleta per cognome o per il nome della sua squadra.
5. La classe **Visualizza** contiene il form *Visualizza*. Da esso è possibile visualizzare tutta la lista degli atleti presenti nel database o di visualizzare atleti filtrati per il loro cognome o per la loro squadra di appartenenza.

![Screenshot Programma](https://github.com/nicosanti98/PDGT-TournamentManagement/blob/master/img/testclientp.png "Screenshot Programma")

### Tournament Management Sito Web

Il sito è composto da diverse pagine. La pagina che fa riferimento all'API contiene un form in cui è possibile inserire l'anagrafica di un iscritto. Esso è pensato per essere fruibile da chiunque voglia iscriversi al torneo in maniera autonoma e veloce.

Il sito richiama l'API per sapere a che ID va inserito il nuovo iscritto, per poi effettuare una richiesta POST al server inserendo quindi i dati nel database. L'autenticazione viene mascherata dallo sviluppatore inserendo le credenziali codificate nel codice, quest'ultimo non visibile dal sito, ma scritto in un file esterno.



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






