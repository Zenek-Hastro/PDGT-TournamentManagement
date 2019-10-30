# PDGT - Tournament Management
##### Sviluppatore: 
Nicolò Santini, mat. 292404

[========]
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

------------
