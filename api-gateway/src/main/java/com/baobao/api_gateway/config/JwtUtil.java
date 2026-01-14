package com.baobao.api_gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.HexFormat;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${app.auth.tokenSecret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init(){
        byte[] keyBytes = HexFormat.of().parseHex(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private Key getSignKey(String secretKey){
        log.info("Key code: {}", new String(HexFormat.of().parseHex(secretKey)));
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public boolean verifyToken(String token){
        try{
            log.info("Token ************ {}", token);
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (Exception ex){
            log.info("Error verify token: {}", token, ex);
            return false;
        }
    }
}
