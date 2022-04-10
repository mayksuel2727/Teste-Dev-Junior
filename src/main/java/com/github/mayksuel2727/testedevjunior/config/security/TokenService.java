package com.github.mayksuel2727.testedevjunior.config.security;

import com.github.mayksuel2727.testedevjunior.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String createToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date today = new Date();
        Date dateExpiration = new Date(today.getTime()+Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("Api teste dev junior")
                .setSubject(logado.getId().toString())
                .setIssuedAt(today)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
