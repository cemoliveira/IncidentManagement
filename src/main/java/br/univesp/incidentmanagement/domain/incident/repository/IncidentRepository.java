package br.univesp.incidentmanagement.domain.incident.repository;

import br.univesp.incidentmanagement.domain.incident.enums.Status;
import br.univesp.incidentmanagement.domain.incident.model.Incident;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IncidentRepository {
    Incident save(Incident incident);
    Page<Incident> findAll(Pageable pageable);
    Page<Incident> findAllActive(Pageable pageable);
    Page<Incident> findAllWaiting(Pageable pageable);
    Page<Incident> findAllProgressing(Pageable pageable);
    Page<Incident> findAllClosed(Pageable pageable);
    Optional<Incident> findById(Long id);
    Page<Incident> findAllSolved(Pageable pageable);
    Page<Incident> findAllUnsolved(Pageable pageable);
    Long countByStatusIn(List<Status> status);
    Long countByStatus(Status status);
    List<Map<String, Object>> countGroupedByCategory();
    List<Map<String, Object>> countGroupedByType();
    List<Map<String, Object>> countGroupedBySchoolClass();
    List<Map<String, Object>> countGroupedByStudent();
}