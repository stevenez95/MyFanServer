/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.security;

import java.security.Key;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

/**
 *
 * @author steve_000
 */
public class JwtManager {
    
    private final String key="asdfghjjsdfsdfsdfsdfsdfsdfqwerty";
    private Key k = new HmacKey(key.getBytes());
    
    public JwtManager() {
    }
    
    public String jwtGenerate(){      
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("MyFan");  // who creates the token and signs it
        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow();  // when the token was issued/created (now)
        claims.setSubject("subject"); // the subject/principal is whom the token is about
        
        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS so we create a JsonWebSignature object.
        JsonWebSignature jws = new JsonWebSignature();
        
        // The payload of the JWS is JSON content of the JWT Claims
        jws.setPayload(claims.toJson());
        
        // The JWT is signed using the private key
        jws.setKey(k);
        
        
        // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        
        String jwt="";
        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException ex) {
            return "";
        }
        
        return jwt;
    }
    
    public boolean jwtValidate(String jwt){
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer("MyFan") // whom the JWT needs to have been issued by
                .setVerificationKey(k) // verify the signature with the public key
                .build(); // create the JwtConsumer instance
        JwtClaims jwtClaims=null;
        try {
            jwtClaims = jwtConsumer.processToClaims(jwt);
            return true;
        } catch (InvalidJwtException ex) {
            return false;
        }
    }
    
}
