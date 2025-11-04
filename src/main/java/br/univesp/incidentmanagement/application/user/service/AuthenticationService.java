package br.univesp.incidentmanagement.application.user.service;

import br.univesp.incidentmanagement.domain.user.repository.UserRepository;
import br.univesp.incidentmanagement.infra.exception.user.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(user -> {
                    if(!user.getActive()) {
                        throw new UserNotFoundException("Usuário inativo ou excluído: " + username + "!");
                    }
                    return user;
                })
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado: " + username + "!"));
    }
}