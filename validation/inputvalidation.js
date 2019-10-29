const firebase = require('../firebase/firebase');
const moment = require('moment');

//Function that check if there are in db some other users with the same cf passed for argument
async function checkcf(cf){
   var ref = firebase.database().ref();
   var bool;
   var query = ref
    .orderByChild("Codice Fiscale")
    .equalTo(cf);
  var snapshot = await query.once("value");
  var value = snapshot.val();
  if(value != undefined)
    return false; 
  else 
    return true; 
}



function checkdate(data){
  var bool; 
  if (data == " ")
    return bool = true;
  else
    bool = moment(data, "DD/MM/YYYY", true).isValid();
  return bool;
}


exports.checkcf = checkcf;
exports.checkdate = checkdate;
