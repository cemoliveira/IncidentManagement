package br.univesp.incidentmanagement.infra.persistence.incident;

import br.univesp.incidentmanagement.domain.incident.enums.Status;
import br.univesp.incidentmanagement.domain.incident.model.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface JpaIncidentRepository extends JpaRepository<Incident, Long> {

    @Query("""
        SELECT o
        FROM Ocorrencia o
        WHERE
        o.deleted = false
        AND o.status IN ('AGUARDANDO_ATENDIMENTO', 'EM_ATENDIMENTO')
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
        AND o.status IN ('SOLUCIONADA', 'ENCERRADA_SEM_SOLUÇÃO')
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

    @Query("""
        SELECT COUNT(o)
        FROM Ocorrencia o
        WHERE
        o.status IN :status
        AND
        o.deleted = false
    """)
    Long countByStatusIn(@Param("status") List<Status> status);

    @Query("""
        SELECT COUNT(o)
        FROM Ocorrencia o
        WHERE
        o.status = :status
        AND
        o.deleted = false
    """)
    Long countByStatus(@Param("status") Status status);

    @Query("""
        SELECT o.category AS key,
        COUNT(o) AS value
        FROM Ocorrencia o
        WHERE
        o.deleted = false
        GROUP BY o.category
    """)
    List<Map<String, Object>> countGroupedByCategory();

    @Query("""
        SELECT o.type AS key,
        COUNT(o) AS value
        FROM Ocorrencia o
        WHERE
        o.deleted = false
        GROUP BY o.type
    """)
    List<Map<String, Object>> countGroupedByType();

    @Query("""
        SELECT o.schoolClass.name AS key,
        COUNT(o) AS value
        FROM Ocorrencia o
        WHERE
        o.deleted = false
        GROUP BY o.schoolClass.name
    """)
    List<Map<String, Object>> countGroupedBySchoolClass();

    @Query("""
        SELECT o.student.name AS key,
        COUNT(o) AS value
        FROM Ocorrencia o
        WHERE
        o.deleted = false
        GROUP BY o.student.name
    """)
    List<Map<String, Object>> countGroupedByStudent();
}