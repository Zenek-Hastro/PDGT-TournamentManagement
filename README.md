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
##Relazione


#### Architettura e scelte implementative
![Architettura Servizio](https://github.com/nicosanti98/PDGT-TournamentManagement/blob/master/img/apistruct.png "Architettura Servizio")
