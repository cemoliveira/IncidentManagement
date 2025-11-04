package br.univesp.incidentmanagement.domain.schoolclass.repository;

import br.univesp.incidentmanagement.domain.schoolclass.model.SchoolClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SchoolClassRepository {
    SchoolClass save(SchoolClass schoolClass);
    Page<SchoolClass> findAllNotCanceled(Pageable pageable);
    Optional<SchoolClass> findById(Long id);
    Page<SchoolClass> findAllCanceled(Pageable pageable);
}