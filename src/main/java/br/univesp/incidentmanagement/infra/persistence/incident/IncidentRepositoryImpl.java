package br.univesp.incidentmanagement.infra.persistence.incident;

import br.univesp.incidentmanagement.domain.incident.model.Incident;
import br.univesp.incidentmanagement.domain.incident.repository.IncidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IncidentRepositoryImpl implements IncidentRepository {
    private final JpaIncidentRepository jpaIncidentRepository;

    public IncidentRepositoryImpl(JpaIncidentRepository jpaIncidentRepository) {
        this.jpaIncidentRepository = jpaIncidentRepository;
    }

    @Override
    public Incident save(Incident incident) {
        return jpaIncidentRepository.save(incident);
    }

    @Override
    public Page<Incident> findAll(Pageable pageable) {
        return jpaIncidentRepository.findAll(pageable);
    }

    @Override
    public Page<Incident> findAllWaiting(Pageable pageable) {
        return jpaIncidentRepository.findAllWaiting(pageable);
    }

    @Override
    public Page<Incident> findAllProgressing(Pageable pageable) {
        return jpaIncidentRepository.findAllProgressing(pageable);
    }

    @Override
    public Page<Incident> findAllActive(Pageable pageable) {
        return jpaIncidentRepository.findAllActive(pageable);
    }

    @Override
    public Page<Incident> findAllSolved(Pageable pageable) {
        return jpaIncidentRepository.findAllSolved(pageable);
    }

    @Override
    public Page<Incident> findAllUnsolved(Pageable pageable) {
        return jpaIncidentRepository.findAllUnsolved(pageable);
    }

    @Override
    public Page<Incident> findAllClosed(Pageable pageable) {
        return jpaIncidentRepository.findAllClosed(pageable);
    }

    @Override
    public Optional<Incident> findById(Long id) {
        return jpaIncidentRepository.findById(id);
    }
}