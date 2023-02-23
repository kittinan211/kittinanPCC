const jwt =require("jsonwebtoken");
const tokenData = 'ca0149f7-dccc-45d2-883b-654c67fecd63';


class TokenManager{
   
    static checkAuthentication(request){
        try{
            let accessToken = request.headers.authorization.split(" ")[1];
            let jwtResponse = jwt.verify(String(accessToken),tokenData);
            return jwtResponse;
        }catch(error){
            return false;
        }
    }


    static getSecret(){
        return require("crypto").randomBytes(64).toString("hex");
    }
}

module.exports = TokenManager;