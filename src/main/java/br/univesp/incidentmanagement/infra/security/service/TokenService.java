package br.univesp.incidentmanagement.infra.security.service;

import br.univesp.incidentmanagement.domain.user.model.User;
import br.univesp.incidentmanagement.infra.security.exception.TokenJWTException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenService {
    private final TokenBlackListService tokenBlackListService;

    @Value("${api.security.token.secret}")
    private String secret;
    private String issuer = "API Gerenciamento de Ocorrências";

    public TokenService(TokenBlackListService tokenBlackListService) {
        this.tokenBlackListService = tokenBlackListService;
    }

    public String createToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getLogin())
                    .withExpiresAt(expiresDate())
                    .sign(algorithm);
        } catch(JWTCreationException e) {
            throw new TokenJWTException("Erro ao gerar o token JWT! ", e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch(JWTVerificationException e) {
            throw new TokenJWTException("Token JWT inválido ou expirado! ", e);
        }
    }

    public Instant expiresDate() {
        return LocalDateTime.now()
                .plusHours(1)
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }

    public Instant getExpirationDate(String tokenJWT) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        var decodedJWT = JWT.require(algorithm)
                .build()
                .verify(tokenJWT);
        return decodedJWT.getExpiresAt().toInstant();
    }
}