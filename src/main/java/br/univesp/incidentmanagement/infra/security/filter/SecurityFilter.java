package br.univesp.incidentmanagement.infra.security.filter;

import br.univesp.incidentmanagement.domain.user.model.User;
import br.univesp.incidentmanagement.domain.user.repository.UserRepository;
import br.univesp.incidentmanagement.infra.security.service.TokenBlackListService;
import br.univesp.incidentmanagement.infra.security.service.TokenService;
import br.univesp.incidentmanagement.infra.security.util.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final TokenBlackListService tokenBlackListService;

    public SecurityFilter(
            TokenService tokenService,
            UserRepository userRepository,
            TokenBlackListService tokenBlackListService
    ) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.tokenBlackListService = tokenBlackListService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJWT = TokenUtils.extractToken(request);
        if(tokenJWT != null) {
            if(tokenBlackListService.isBlacklisted(tokenJWT)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            String subject = tokenService.getSubject(tokenJWT);
            Optional<User> userOpt = userRepository.findByLogin(subject);
            if(userOpt.isPresent()) {
                UserDetails user = userOpt.get();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authentication.setDetails(tokenJWT);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}