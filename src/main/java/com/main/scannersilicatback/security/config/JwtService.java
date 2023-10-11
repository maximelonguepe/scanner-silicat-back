package com.main.scannersilicatback.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Getter
@Service
public class JwtService {
    private static final String SECRET_KEY = "V7dHF3WQ0VsRfMCkXuwtnt8hqpiToo8SPB51pamDGlXaQ9+dBKMkWKlNkMjJTVIa4jSKP1HEZP1UAHzj2D3Lgzb/TrsOwsgZP+Rd8tZ6ngx8tDHyHRjU/kfwoY4rUpWboJFq/8MHJQLyfMuNUTt9TH80LY1loUFwjZYeK0n3UgHLBzfmDPKJlT9le03quxPXIW0SsoT4W8UXo32lR2w9xSv4ZsI+xsrx57AHJU71HemhlKLQyMe3AstEI+ZlTTXu303RxzIvCMoItB3k+K/UFANnnQzqFUkYDeUn9qRqokuzF0F0fY82T+t5NKJg6sW0BbC+wQA5MPe/tJLLg8E5j7gLHG9YjyfrNCcDmC9peaw=";

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS384)
                .compact();

    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String usename = extractUsername(token);
        return (usename.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
