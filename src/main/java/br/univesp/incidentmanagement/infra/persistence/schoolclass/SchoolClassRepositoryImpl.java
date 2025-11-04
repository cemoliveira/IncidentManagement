package br.univesp.incidentmanagement.infra.persistence.schoolclass;

import br.univesp.incidentmanagement.domain.schoolclass.model.SchoolClass;
import br.univesp.incidentmanagement.domain.schoolclass.repository.SchoolClassRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SchoolClassRepositoryImpl implements SchoolClassRepository {
    private final JpaSchoolClassRepository jpaSchoolClassRepository;

    public SchoolClassRepositoryImpl(
            JpaSchoolClassRepository jpaSchoolClassRepository
    ) {
        this.jpaSchoolClassRepository = jpaSchoolClassRepository;
    }

    @Override
    public SchoolClass save(SchoolClass schoolClass) {
        return jpaSchoolClassRepository.save(schoolClass);
    }

    @Override
    public Page<SchoolClass> findAllNotCanceled(Pageable pageable) {
        return jpaSchoolClassRepository.findAllNotCanceled(pageable);
    }

    @Override
    public Optional<SchoolClass> findById(Long id) {
        return jpaSchoolClassRepository.findById(id);
    }

    @Override
    public Page<SchoolClass> findAllCanceled(Pageable pageable) {
        return jpaSchoolClassRepository.findAllCanceled(pageable);
    }
}