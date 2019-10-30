var httprequests = new XMLHttpRequest();
var url;
var contatoretasti = 0;

async function Check() {
   

    // Variabili associate ai campi del modulo
    var name = document.modulo.name.value;
    var surname = document.modulo.surname.value;
    var data = document.modulo.data.value;
    var luogo = document.modulo.luogo.value;
    var residenza = document.modulo.residenza.value;
    var cf = document.modulo.cf.value.toUpperCase();
    var cell = document.modulo.cell.value;
    var mail = document.modulo.mail.value;
    var teamname = document.modulo.teamname.value;

    // Espressione regolare dell'email
    var email_reg_exp = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$/;


    //Effettua il controllo sul campo NOME
    if ((name == "") || (name == "undefined")) {
        alert("Il campo Nome è obbligatorio.");
        document.modulo.name.focus();
        return false;
    }

    //Effettua il controllo sul campo COGNOME
    else if ((surname == "") || (surname == "undefined")) {
        alert("Il campo Cognome è obbligatorio.");
        document.modulo.surname.focus();
        return false;
    }

    //Effettua il controllo sul campo DATA DI NASCITA
    else if (document.modulo.data.value.substring(2, 3) != "/" ||
        document.modulo.data.value.substring(5, 6) != "/" ||
        isNaN(document.modulo.data.value.substring(0, 2)) ||
        isNaN(document.modulo.data.value.substring(3, 5)) ||
        isNaN(document.modulo.data.value.substring(6, 10))) {
        alert("Inserire la data di nascita in formato GG/MM/AAAA");
        document.modulo.data.value = "";
        document.modulo.data.focus();
        return false;
    }
    else if (document.modulo.data.value.substring(0, 2) > 31) {
        alert("Impossibile utilizzare un valore superiore a 31 per i giorni");
        document.modulo.data.select();
        return false;
    }
    else if (document.modulo.data.value.substring(3, 5) > 12) {
        alert("Impossibile utilizzare un valore superiore a 12 per i mesi");
        document.modulo.data.value = "";
        document.modulo.data.focus();
        return false;
    }
    else if (document.modulo.data.value.substring(6, 10) <= 1900) {
        alert("Impossibile utilizzare un valore inferiore a 1900 per l'anno");
        document.modulo.data.value = "";
        document.modulo.data.focus();
        return false;
    }

    //Effettua il controllo sul campo Luogo di nascita'
    else if ((luogo == "") || (luogo == "undefined")) {
        alert("Il campo Luogo di Nascita è obbligatorio.");
        document.modulo.luogo.focus();
        return false;
    }

    //Effettua il controllo sul campo Residenza
    else if ((residenza == "") || (residenza == "undefined")) {
        alert("Il campo Luogo di residenza è obbligatorio.");
        document.modulo.residenza.focus();
        return false;
    }
    //Effettua il controllo sul campo Cellulare
    else if ((isNaN(cell)) || (cell == "") || (cell == "undefined")) {
        alert("Il campo Telefono è numerico ed obbligatorio.");
        document.modulo.cell.value = "";
        document.modulo.cell.focus();
        return false;
    }
    else if (!email_reg_exp.test(mail) || (mail == "") || (mail == "undefined")) {
        alert("Inserire un indirizzo email corretto.");
        document.modulo.mail.select();
        return false;
    }

    //INVIA IL MODULO
    else {
        sendPost();
        return;
        
    }
}


function sendPost() {
    try {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', "https://tournament-manage.herokuapp.com/tournament/players/number", false);
        xhr.send();
        var n = xhr.responseText;
        console.log(n);
        url = "https://tournament-manage.herokuapp.com/tournament/players?name=" + document.modulo.name.value.replace(" ", "%20") +
            "&surname=" + document.modulo.surname.value.replace(" ", "%20") + "&data=" + document.modulo.data.value +
            "&luogo=" + document.modulo.luogo.value.replace(" ", "%20") + "&cf=" + document.modulo.cf.value.toUpperCase() +
            "&residenza=" + document.modulo.residenza.value.replace(" ", "%20") +
            "&cell=" + document.modulo.cell.value + "&mail=" + document.modulo.mail.value + "&teamname=" +
            document.modulo.teamname.value.replace(" ", "%20") + "&len=" + n;
        console.log(url);
        var c = confirm(
            "Controlla che i dati che hai inserito siano corretti, non potrai modificarli (perlomeno per ora...🤫😏)" + "\n" + "\n" +
            "Nome: " + "\t" + "\t" + "\t" + "\t" + document.modulo.name.value.toUpperCase() + "\n" +
            "Cognome: " + "\t" + "\t" + "\t" + document.modulo.surname.value.toUpperCase() + "\n" +
            "Data di nascita: " + "\t" + "\t" + document.modulo.data.value.toUpperCase() + "\n" +
            "Luogo di nascita: " + "\t" + "\t" + document.modulo.luogo.value.toUpperCase() + "\n" +
            "Città di residenza: " + "\t" + document.modulo.residenza.value.toUpperCase() + "\n" +
            "CF: " + "\t" + "\t" + "\t" + "\t" + "\t" + document.modulo.cf.value.toUpperCase() + "\n" +
            "Cellulare: " + "\t" + "\t" + "\t" + document.modulo.cell.value.toUpperCase() + "\n" +
            "e-Mail: " + "\t" + "\t" + "\t" + "\t" + document.modulo.mail.value.toUpperCase() + "\n" +
            "Squadra: " + "\t" + "\t" + "\t" + document.modulo.teamname.value.toUpperCase() + "\n"

        );
        console.log(c);
        if (c == true) {
            console.log()
            httprequests.open('POST', url);
            httprequests.setRequestHeader('Authorization', 'BASIC YWRtaW46a290YzIwMjA=');
            httprequests.setRequestHeader('Content-Type', 'application/json');
            httprequests.send(null);
            httprequests.onreadystatechange = async function () {
                if (httprequests.readyState === 4) {
                    var serverResponse = await httprequests.responseText;
                    console.log(serverResponse);
                    if (serverResponse === 'Forbidden') {
                        alert("Atleta con questo codice fiscale già presente");
                        return;
                    }
                    window.location.href = 'ins_baller2.html';
                }
                else
                    return;
            };
        }
        else {
            return;
        }

    }
    catch (err) {
        return false;
    }
}





            
