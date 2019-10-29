//Add libraries
const express = require('express');
const app = express();
const dotenv = require('dotenv');
dotenv.config();
const XMLHttpRequest = require('xmlhttprequest').XMLHttpRequest;
const bodyParser = require('body-parser');
const inputvalidation = require('./validation/inputvalidation');
const firebase = require('./firebase/firebase');
const auth = require('./authentication/auth');

//Middleweares
app.use(bodyParser());
app.use(express.Router());
app.use(function(req, res, next){
     res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE'); // If needed
  if(req.method==='OPTIONS'){
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,Content-Type, Authorization');
  }
    next();
  });

var Http = new XMLHttpRequest();

app.get('/tournament/players/number/', function(req, res){
  
  const url='https://quickstart-1565185583126.firebaseio.com/.json'; 
  var myObj; 
    
  //Open the connection with Firebase
  Http.open("GET", url);
  Http.send();
  
  var n;
  Http.onreadystatechange = async function() {
    if (this.readyState == 4 && this.status == 200) {
        myObj = await JSON.parse(this.responseText);
        if (myObj == undefined)
          n = 0;
        else
          n = await myObj.length; 
      res.send(JSON.stringify(n));
        
    };
    
  
  };  
  
})

//Returns API info in JSON format
app.get('/info', function(req, res){
    const info = {
        "api name": "pdgt api",
        "developer": "nicosanti98",
        "developer mail": "nicolo_santini@live.it",
        "version": "1.0.0", 
        "github": "https://github.com/nicosanti98/PDGTapi",
        "scopo": "L'app, mediante 2 chiamate get, si prefigge di inserire e di leggere da e in un database di Firebase i dati fiscali degli iscritti di un ipotetico torneo"
    }
    
    res.send(info);
});

app.get('/login', function(req,res){
       //Calls the auth function, if results false, returns 401-Unauthorized
     if(!auth.attemptAuth(req)){
        res.sendStatus(401);
        res.end();
        return; 
    }
     else{
        //Else returns 200-OK
         res.sendStatus(200);
         res.end();
         return;
     }
 });



//Returns all data in Firebase
app.get('/tournament/players', function(req, res){
    //Open HTTP connection in this URL
    const url='https://quickstart-1565185583126.firebaseio.com/.json'; 
    
  var myObj; 
    
  //Open the connection with Firebase
  Http.open("GET", url);
  Http.send();
  
  Http.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        myObj = JSON.parse(this.responseText);
        console.log(myObj);
        res.send(myObj);               
    };
  };  
});


//Returns anagrphic data of player searched by name and surname
app.get("/tournament/players/searchbyname/", function(req, res){
  var ref = firebase.database().ref("/");
    var cognome = req.query.surname;
    var cognome = cognome.toUpperCase();
    //This var is the query string to get correct data from Firebase
    var query = ref
      .orderByChild("Cognome")
      .equalTo(cognome);
    //In order to communicate data, we need to use an asyncronous function
    query.on("value", async function(snapshot){
      var obj = await snapshot.val();
      //If object is null we'll return 404 error
      if(obj == null)
        res.sendStatus(404);
      else
        {
          //else we need to work with strings cause of the object format
          //first of all we need to convert the json object in a string to work with it
          obj = JSON.stringify(obj);
          //if the string has not some "null" occourences
          if(obj.indexOf("null") == -1)
            {
              //we check if there are the char ":" before of the 13th char
              if (obj.indexOf(":")<13)
                {
                  //in this case we save the position of ":" and we get the substring from the char next to the char ":"
                  obj = obj.substring(obj.indexOf(":")+1, obj.length-1);
                  //We make an "array of objects"
                  obj = "["+obj+"]";
                  //We send the response parsing the obj in json
                  res.send(JSON.parse(obj));
                }
              else
                //otherwise the obj format is ok, so we send it
                res.send(obj);
            }
          else
            {
              //If there are some occurences of "null", we replace it with "" and send the obj
              obj = obj.replace(/null,/g, "");
              res.send(obj);
            }

        }
  })
});

//Returns anagraphic of players searched by team name
app.get("/tournament/players/searchbyteam/", function(req, res){
  var ref = firebase.database().ref();
  var nomesquadra = req.query.teamname; 
  var nomesquadra = nomesquadra.toUpperCase();
  var query = ref
    .orderByChild("Squadra")
    .equalTo(nomesquadra);
  
  query.on("value", async function(snapshot){
    var obj = await snapshot.val();
      //In this case we work with strings as the same way of the previous method
      if(obj == null)
        res.sendStatus(404);
      else
        {

          obj = JSON.stringify(obj);
          if(obj.indexOf("null") == -1)
            {
              if (obj.indexOf(":")<13)
                {
                  obj = obj.substring(obj.indexOf(":")+1, obj.length-1);
                  obj = "["+obj+"]";
                  res.send(JSON.parse(obj));
                }
              else
                res.send(obj);
            }
          else
            {
              obj = obj.replace(/null,/g, "");
              res.send(obj);
            }

        }
  });
})

//This method allows the authenticated user to delete players from database
app.delete('/tournament/players/', function(req,res){
  if(!auth.attemptAuth(req)){
     console.log("Failed authentication");
     res.sendStatus(401);
     res.end();
     return; 
 }  
  else{
    //The id of the player may to be passed as query in the URL
    var id = req.query.id;
    
    
    //Open HTTP connection in this URL
    const url='https://quickstart-1565185583126.firebaseio.com/.json'; 
     
    
  //Open the connection with Firebase
  Http.open("GET", url);
  Http.send();
  //The user with ref = id will be removed
  var ref = firebase.database().ref('/'+id);
  ref.remove();
  Http.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var obj = JSON.parse(this.responseText); 
        //We need to know the length of the obj, that is also the number of elements in db
        var length = obj.length;
        if(id == (length))
          {
            //If the id passed is equal to the obj.length, we don't need to do anymore. Send OK-200
            res.send(200);
          }
        else
          {
              //Else we have to manage the string
                var obj1 = JSON.stringify(obj);

                    if(obj1.indexOf("null") == -1)
                      {
                        if (obj1.indexOf(":")<13)
                          {
                            obj1 = obj1.substring(obj1.indexOf(":")+1, obj1.length-1);
                            obj1 = "["+obj1+"]";
                          }
                      }
                    else
                      {
                        obj1 = obj1.replace(/null,/g, "");
                        obj1 = obj1.replace(/,null,/g, "");
                      }
              //And we parse the string in json obj
              obj1 = JSON.parse(obj1);
              
              //We need to itherate this procedure until i = length-1
              for (var i = 0; i < length-1; i++)
                {
                  //Passing into variables the obj[i] values
                  var cellulare = obj1[i]["Cellulare"];
                  var cf = obj1[i]["Codice Fiscale"];
                  var cognome = obj1[i]["Cognome"];
                  var data = obj1[i]["Data di nascita"];
                  var luogo = obj1[i]["Luogo di nascita"];
                  var residenza = obj1[i]["Luogo residenza"];
                  var mail = obj1[i]["Mail"];
                  var nome = obj1[i]["Nome"];
                  var squadra = obj1[i]["Squadra"];
                  //We make the JSON obj
                  var dati = {
                      "Nome": nome.toUpperCase(),
                      "Cognome": cognome.toUpperCase(), 
                      "Data di nascita": data.toUpperCase(), 
                      "Luogo di nascita": luogo.toUpperCase(), 
                      "Codice Fiscale": cf.toUpperCase(),
                      "Luogo residenza": residenza.toUpperCase(), 
                      "Mail": mail.toUpperCase(), 
                      "Cellulare": cellulare.toUpperCase(),
                      "Squadra": squadra.toUpperCase()
                    };
                    //Send variable "dati" in database
                    firebase.database().ref('/'+i).set(dati); 
                }
              console.log("Remove-->OK");
              //Removes last element from firebase, because the other elements are in ref(i-1)
              firebase.database().ref('/'+(length-1)).remove(); 
              res.sendStatus(200);
      
           
          }
    };
    
  };  
    
    
    
    
  }
  
});


//Method that allows the user, if is authenticathed, to insert a new player in the database,
//passing the anagraphic data in the body request
app.post('/tournament/players/',async function(req, res){
 //Try if the user isn't logged in  
 if(!auth.attemptAuth(req)){
     console.log("Failed authentication");
     res.sendStatus(401);
     res.end();
     return; 
 }  
  else{
        //if the authentication is ok, reads the requests' body
          console.log("Authentication OK");
          var nome = req.query.name;
          var cognome = req.query.surname;
          var datanascita = req.query.data;
          var luogonascita = req.query.luogo;
          var codicefiscale = req.query.cf;
          var cittaresidenza = req.query.residenza;
          var cellulare = req.query.cell; 
          var email = req.query.mail; 
          var teamname = req.query.teamname; 
          var lenght = req.query.len;
        
         var bool = await inputvalidation.checkcf(codicefiscale);
          if(inputvalidation.checkdate(datanascita) == false){
            res.sendStatus(406);
            res.end();
          }
          //The method checks if there are some players with the same cf that users want to add in firebase
          //In this case will return 404 because a player with the same cf couldn't be added, otherwise the method
          //will insert the anagraphic data in database
          
          if(!bool)
          {
            res.sendStatus(403);
            res.end();
            return;
          }
          else
          {
            var data = {
              "Nome": nome.toUpperCase(),
              "Cognome": cognome.toUpperCase(), 
              "Data di nascita": datanascita.toUpperCase(), 
              "Luogo di nascita": luogonascita.toUpperCase(), 
              "Codice Fiscale": codicefiscale.toUpperCase(),
              "Luogo residenza": cittaresidenza.toUpperCase(), 
              "Mail": email.toUpperCase(), 
              "Cellulare": cellulare.toUpperCase(),
              "Squadra": teamname.toUpperCase(),
            };
            console.log(data);
            console.log(lenght);
            firebase.database().ref('/'+lenght).set(data);
            res.sendStatus(200);
            return; 
         }    
    }

  });
  
  


var server = app.listen((process.env.PORT || 5000), function () {
    var host = server.address().address;
    var port = server.address().port;

    console.log("Server up, listening at http://%s:%s", host, port);
});
