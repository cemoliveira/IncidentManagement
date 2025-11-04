package br.univesp.incidentmanagement.domain.user.repository;

import br.univesp.incidentmanagement.domain.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Page<User> findAllActive(Pageable pageable);
    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
}