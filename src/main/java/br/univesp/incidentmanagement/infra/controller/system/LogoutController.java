package br.univesp.incidentmanagement.infra.controller.system;

import br.univesp.incidentmanagement.application.user.service.LogoutService;
import br.univesp.incidentmanagement.infra.security.util.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class LogoutController {
    private final LogoutService logoutService;

    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @PostMapping
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String tokenJWT = TokenUtils.extractToken(request);
        if (tokenJWT == null) {
            return ResponseEntity.badRequest().body("Token JWT não encontrado no cabeçalho da requisição!");
        }
        try {
            logoutService.logout(tokenJWT);
            return ResponseEntity.ok("Logout realizado com sucesso! Token JWT descartado!");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Token JWT inválido ou expirado!");
        }
    }
}