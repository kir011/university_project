package com.alpha.studio.code.university.project.adapter.security.jwt;

import com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.user.UserDocument;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value("${spring.token.expiration}")
    private String expiration;
    @Value("${spring.token.assignatureKey}")
    private String assignatureKey;

    public String tokenGenerate(UserDetails document){
        long expirationString = Long.valueOf(expiration);
        LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(expirationString);
        Instant instant = expirationDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(document.getUsername())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, assignatureKey)
                .compact();
    }

    private Claims decodeToken(String token) throws ExpiredJwtException {
        return Jwts.parser().setSigningKey(assignatureKey).parseClaimsJws(token).getBody();
    }

    public boolean tokenIsValid(String token){
        try{
            Claims claims = decodeToken(token);
            Date dateExpiration = claims.getExpiration();
            LocalDateTime expirationLocal = dateExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(expirationLocal);
        }catch(Exception ex){
            return false;
        }
    }

    public String getLogin(String login) throws ExpiredJwtException {
        return (String) decodeToken(login).getSubject();
    }
}
