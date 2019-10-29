var firebase = require('firebase');

//Firebase configuration params
const firebaseConfig = {
    apiKey: "AIzaSyBppN1M8YgSp1X44DtLp9MRkZW_kWIKXpM",
    authDomain: "quickstart-1565185583126.firebaseapp.com",
    databaseURL: "https://quickstart-1565185583126.firebaseio.com",
    projectId: "quickstart-1565185583126",
    storageBucket: "quickstart-1565185583126.appspot.com",
    messagingSenderId: "409635634731",
    appId: "1:409635634731:web:5cfd44950001f9df2e746e"
  };
firebase.initializeApp(firebaseConfig); //Initialize Firebase app
var database = firebase.database();

module.exports = firebase; 
exports.database = database;
