package org.shrinidhi.placementbackendportal.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.security.Key;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.io.Decoders;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class JwtUtil {
    private static final String SECRET = "supersecretkeysupersecretkeysupersecretkey"; // 32+ chars for HS256
    private static final SecretKey key = new SecretKeySpec(SECRET.getBytes(), "HmacSHA256");
    private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 24 hours

    public static String generateToken(String email, String role) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date(now))
                .expiration(new Date(now + EXPIRATION))
                .signWith(key)
                .compact();
    }

    public static Claims validateToken(String token) {
        JwtParser parser = Jwts.parser().verifyWith(key).build();
        Jws<Claims> jws = parser.parseSignedClaims(token);
        return jws.getPayload();
    }
}
