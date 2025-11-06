package br.univesp.incidentmanagement.infra.controller.system;

import br.univesp.incidentmanagement.application.user.dto.AuthenticationDTO;
import br.univesp.incidentmanagement.application.user.service.LoginService;
import br.univesp.incidentmanagement.infra.security.dto.TokenJWTDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    private final LoginService loginService;

    public AuthenticationController(LoginService loginService) {
        this.loginService = loginService;
    }

    @CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
    @PostMapping
    public ResponseEntity<TokenJWTDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        TokenJWTDTO token = loginService.login(data);
        return ResponseEntity.ok(token);
    }
}