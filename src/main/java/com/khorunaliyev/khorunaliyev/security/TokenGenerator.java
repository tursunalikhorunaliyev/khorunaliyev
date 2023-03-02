package com.khorunaliyev.khorunaliyev.security;

import com.khorunaliyev.khorunaliyev.extra.SecurityVariables;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenGenerator {
    public String generateToken(Authentication authentication) {
        final String username = authentication.getName();
        final Date issuedDate = new Date();
        final Date expirationDate = new Date(issuedDate.getTime() + SecurityVariables.VALIDATE);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issuedDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SecurityVariables.SECRET)
                .compact();
    }

    public String getUsernameByToken(String token){
        final Claims claims = Jwts.parser().setSigningKey(SecurityVariables.SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            final Claims claims = Jwts.parser().setSigningKey(SecurityVariables.SECRET).parseClaimsJws(token).getBody();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public String getTokenFromRequest(HttpServletRequest request){
        final String header = request.getHeader("Authorization");
        if(header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;

    }
}
