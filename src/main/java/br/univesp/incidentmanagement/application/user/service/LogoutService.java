package br.univesp.incidentmanagement.application.user.service;

import br.univesp.incidentmanagement.infra.security.service.TokenBlackListService;
import br.univesp.incidentmanagement.infra.security.service.TokenService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LogoutService {
    private final TokenService tokenService;
    private final TokenBlackListService tokenBlackListService;

    public LogoutService(
            TokenService tokenService,
            TokenBlackListService tokenBlackListService
    ) {
        this.tokenService = tokenService;
        this.tokenBlackListService = tokenBlackListService;
    }

    public void logout(String tokenJWT) {
        Instant expiration = tokenService.getExpirationDate(tokenJWT);
        tokenBlackListService.add(tokenJWT, expiration);
    }
}