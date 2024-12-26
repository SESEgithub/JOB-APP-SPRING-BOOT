package com.rest.JobApp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {


private String secreteKey;

    public JwtService() {
        secreteKey = generateSecretKey();
    }

    public String generateSecretKey(){

    try {
        KeyGenerator keyGenerator=KeyGenerator.getInstance("HMACSHA256");
        SecretKey secretKey=keyGenerator.generateKey();
        System.out.println(" generated secrete key is :" +secretKey.toString());
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(" ERROR IN GENERATING SECRETE KEY");
    }

}


    public String generateToken(String userName) {
        Map<String , Object> tokenBuilders=new HashMap<>();
            return Jwts.builder()
                    .setClaims(tokenBuilders)
                    .setSubject(userName)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000*60*50))
                    .signWith(getGeneratedKey() , SignatureAlgorithm.HS256 ).compact();
    }

    private Key getGeneratedKey() {
        byte[] secretKeyBytes= Decoders.BASE64.decode(secreteKey);
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }

    public String extractUserName(String token) {

    return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsTFunction ) {
      final Claims claims=extractAllClaims(token);
            return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
    return Jwts.parser()
            .setSigningKey(getGeneratedKey())
            .build().parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
      final String usrName=extractUserName(token);
        return (usrName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token , Claims::getExpiration);
    }
}
