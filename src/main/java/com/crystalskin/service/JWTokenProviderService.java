package com.crystalskin.service;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.crystalskin.config.JwtProperties;
import com.crystalskin.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JWTokenProviderService {
    private final JwtProperties jwtProperties;
    private SecretKey key;
    private JwtParser parser;

    public String createToken(User user, Duration expiredAt) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expiredAt.toMillis());
        return Jwts.builder()
                .header()
                .add(getHeader())
                .and().claims()
                .issuedAt(now)
                .issuer(jwtProperties.getIssuer())
                .subject(user.getUsrId())
                .expiration(exp)
                .and()
                .signWith(getKey(), Jwts.SIG.HS256).compact();
    }

    public boolean isValidToken(String token) {
        if (parser == null) {
            parser = Jwts.parser().verifyWith(getKey()).build();
        }
        try {
            parser.parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        if (parser == null) {
            parser = Jwts.parser().verifyWith(getKey()).build();
        }
        try {
            Jws<Claims> jws = parser.parseSignedClaims(token);
            return jws.getPayload();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String getUsrId(String token) {
    	Claims claims = getClaims(token);
    	return claims.getSubject();
    }

    private SecretKey getKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProperties.getSecretKey()));
        }
        return key;
    }

    private Map<String, Object> getHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return header;
    }
}
