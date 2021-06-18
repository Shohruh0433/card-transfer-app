package uz.developer.cardtransferapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTprovider {

    static long expire_time=36_000_000; //10 soat
  static   String kalit="Bu tokenni mahfiy sozi";

public static   String generateToken(String username){

    Date expireDate=new Date(System.currentTimeMillis()+expire_time);

    return Jwts
            .builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS512, kalit)
            .compact();
}

public  boolean validateToken(String token){
  try {
      Jwts
              .parser()
              .setSigningKey(kalit)
              .parseClaimsJws(token);
      return true;
  }catch (Exception e){
      e.printStackTrace();
  }
  return false;

}
public String getUserNameFromToken(String token){
    String username = Jwts
            .parser()
            .setSigningKey(kalit)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    return username;

}



}
