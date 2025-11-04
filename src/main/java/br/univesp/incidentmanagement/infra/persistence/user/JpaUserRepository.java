package br.univesp.incidentmanagement.infra.persistence.user;

import br.univesp.incidentmanagement.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {

    @Query("""
        SELECT u
        FROM Usuario u
        WHERE
        u.active = true
    """)
    Page<User> findAllActive(Pageable pageable);

    Optional<User> findByLogin(String login);
}