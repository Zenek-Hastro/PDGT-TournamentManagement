//Adds authentication values read on .env file in a constant
 const admins = [
      [process.env.ADMIN1_USER, process.env.ADMIN1_PASS],
      [process.env.ADMIN2_USER, process.env.ADMIN2_PASS]
  ]

//Login function, returns true if login is ok, false if authentication values doesn't match with the admins' costant ones
function attemptAuth(req) {
  if(!req.headers.authorization) {
    return false;
  }
  if(!req.headers.authorization.startsWith('BASIC ')&&!req.headers.authorization.startsWith('Basic ')) {
    //HTTP Basic auth isn't used
    return false;
  }

  // Decode the base64 string sent by browser
  const authString = req.headers.authorization.substr(6);
  const decodedString = new Buffer(authString, 'base64').toString();
  const [login, password] = decodedString.split(':');
    
  var k = 0;
   //Credentials are check until they are found in the constant admins. If the constant lenght is reached by the counter
   //the iteration will return 0
  for(var i = 0; i<admins.length; i++){
      if(login == admins[i][0] && password == admins[i][1]){
          k++;
          break;
      }
  }
  //If k=0 matches are not found, return false, else return true
  if (k==0)
      return false; 
  else
      return true; 
};

exports.attemptAuth = attemptAuth;



