package br.univesp.incidentmanagement.application.user.service;

import br.univesp.incidentmanagement.application.user.dto.AuthenticationDTO;
import br.univesp.incidentmanagement.domain.user.model.User;
import br.univesp.incidentmanagement.infra.security.dto.TokenJWTDTO;
import br.univesp.incidentmanagement.infra.security.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public LoginService(
            AuthenticationManager manager,
            TokenService tokenService
    ) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    public TokenJWTDTO login(AuthenticationDTO data) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication authentication = manager.authenticate(token);
        String tokenJWT = tokenService.createToken((User) authentication.getPrincipal());
        return new TokenJWTDTO(tokenJWT);
    }
}