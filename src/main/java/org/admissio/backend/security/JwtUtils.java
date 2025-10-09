package org.admissio.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    public String generateToken(Authentication authResult) {
        return JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.TOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstant.SECRET_KEY));
    }
}
