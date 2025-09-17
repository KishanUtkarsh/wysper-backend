package com.wysper.common.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wysper.common.exception.InvalidJwtTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.UUID;

@Slf4j
public class JwtUtil {

    private JwtUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }


    private static String SECRET_KEY;
    private static final long ACCESS_TOKEN_EXPIRATION = 7L * 24 * 60 * 60 * 1000;  // 7 days
    private static final long REFRESH_TOKEN_EXPIRATION = 14L * 24 * 60 * 60 * 1000; // 14 days

    @Value("${jwt.secret}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    public static String generateJwtToken(UUID uuid, String role, String type) {
        return JWT.create()
                .withIssuer("Wysper")
                .withSubject(String.valueOf(uuid))
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(type.equals("new") ? System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION : System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .sign(getAlgorithm());
    }

    public static DecodedJWT verifyToken(String token) {
        try {
            DecodedJWT decodedJWT =  JWT.require(getAlgorithm())
                    .withIssuer("Wysper")
                    .build()
                    .verify(token);
            if (decodedJWT == null) {
                throw new InvalidJwtTokenException("Invalid JWT token");
            }
            return decodedJWT;
        } catch (Exception e) {
            log.error("Token verification failed: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT validation failed", e);
        }
    }

    public static String getUuidFromToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getSubject();
    }

    public static String getRoleFromToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);

        return decodedJWT.getClaim("role").asString();
    }

    public static boolean isTokenExpired(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getExpiresAt().before(new Date());
    }

    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET_KEY);
    }

    public static Long getAccessTokenExpiration(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getExpiresAt().getTime() - System.currentTimeMillis();
    }

    public static boolean isValidToken(String token) {
        try {
            verifyToken(token);
            return true;
        } catch (ResponseStatusException e) {
            log.error("Invalid JWT token: {}", e.getReason());
            return false;
        }
    }
}
