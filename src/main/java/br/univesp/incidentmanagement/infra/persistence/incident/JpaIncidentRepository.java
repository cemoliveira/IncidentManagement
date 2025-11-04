package br.univesp.incidentmanagement.infra.persistence.incident;

import br.univesp.incidentmanagement.domain.incident.model.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaIncidentRepository extends JpaRepository<Incident, Long> {

    @Query("""
        SELECT o
        FROM Ocorrencia o
        WHERE
        o.deleted = false
        AND
        o.status = 'AGUARDANDO_ATENDIMENTO'
        OR
        o.status = 'EM_ATENDIMENTO'
    """)
    Page<Incident> findAllActive(Pageable pageable);

    @Query("""
        SELECT o
        FROM Ocorrencia o
        WHERE
        o.status = 'AGUARDANDO_ATENDIMENTO'
        AND
        o.deleted = false
    """)
    Page<Incident> findAllWaiting(Pageable pageable);

    @Query("""
        SELECT o
        FROM Ocorrencia o
        WHERE
        o.status = 'EM_ATENDIMENTO'
        AND
        o.deleted = false
    """)
    Page<Incident> findAllProgressing(Pageable pageable);

    @Query("""
        SELECT o
        FROM Ocorrencia o
        WHERE
        o.deleted = false
        AND
        o.status = 'SOLUCIONADA'
        OR
        o.status = 'ENCERRADA_SEM_SOLUÇÃO'
    """)
    Page<Incident> findAllClosed(Pageable pageable);

    @Query("""
        SELECT o
        FROM Ocorrencia o
        WHERE
        o.status = 'SOLUCIONADA'
        AND
        o.deleted = false
    """)
    Page<Incident> findAllSolved(Pageable pageable);

    @Query("""
        SELECT o
        FROM Ocorrencia o
        WHERE
        o.status = 'ENCERRADA_SEM_SOLUÇÃO'
        AND
        o.deleted = false
    """)
    Page<Incident> findAllUnsolved(Pageable pageable);
}